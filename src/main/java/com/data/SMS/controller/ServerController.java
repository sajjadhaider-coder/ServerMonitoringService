package com.data.SMS.controller;

import com.data.SMS.dto.ServerLogsResponse;
import com.data.SMS.dto.ServerRequest;
import com.data.SMS.dto.ServerResponse;
import com.data.SMS.service.ServersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    ServersService serversService;

    @GetMapping("/addServer")
    public ServerResponse addServerForMonitoring(ServerRequest serverRequest) {
        ServerResponse serverResponse = serversService.saveServer(serverRequest);
        return serverResponse;
    }

    @GetMapping("/getServers")
    public List<ServerResponse> getServers() {
        List<ServerResponse> serverResponsesList = null;
        serverResponsesList = serversService.getServers();
        return serverResponsesList;
    }
    @GetMapping("/getLogs")
    public List<ServerLogsResponse> getSysLogs(){
        return serversService.getLogs();
    }
}
