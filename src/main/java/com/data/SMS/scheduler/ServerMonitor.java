package com.data.SMS.scheduler;

import com.data.SMS.dto.ServerLogsRequest;
import com.data.SMS.dto.ServerResponse;
import com.data.SMS.model.Server;
import com.data.SMS.service.ServersService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServerMonitor implements   Runnable {

    @Autowired
    ServersService serversService;

    @Value("${external.api.base-url_ball5}")
    private String apiBaseUrlBall5;

    @Value("${external.api.base-url_ball8}")
    private String apiBaseUrlBall8;

    @Value("${external.api.base-url_ball10}")
    private String apiBaseUrlBall10;

    @Value("${external.api.base-url_ball20}")
    private String apiBaseUrlBall20;

    @Autowired
    ServersService serverLogsService;
     public enum  gamenames {
        LUCKYBALL5, LUCKYBALL8, LUCKYBALL10, LUCKYBALL20
    }
    @Override
    public void run(){
        HttpResponse<String> response = null;

        ServerLogsRequest systemLogs = null;
        ArrayList<String> urls = new ArrayList<>();
        List<ServerResponse> serverList = serversService.getServers();
        if (serverList != null ) {
            for (ServerResponse serverResponse : serverList) {
                try {

                    systemLogs = new ServerLogsRequest();
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(serverResponse.getServerUrl()))
                            .GET()
                            .build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    systemLogs.setStatusCode(String.valueOf(response.statusCode()));
                    if (response.statusCode() == 200) {
                        Document document = Jsoup.parse(response.body());
                        systemLogs.setAccessStatus("SUCCESS");
                    } else {
                        systemLogs.setAccessStatus("FAILED");
                        systemLogs.setErrorText(response.body());
                    }
                    systemLogs.setAccessTime(LocalDateTime.now());
                    systemLogs.setServerUrl(serverResponse.getServerUrl());
                } catch (Exception e) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    assert systemLogs != null;
                    systemLogs.setErrorText(sw.toString()); // Saves full stack trace in DB
                } finally {
                    serverLogsService.saveLogRecord(systemLogs);
                }
            }
        }
    }
}
