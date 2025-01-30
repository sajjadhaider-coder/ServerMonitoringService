package com.data.SMS.service;

import com.data.SMS.dto.ServerLogsRequest;
import com.data.SMS.dto.ServerLogsResponse;
import com.data.SMS.dto.ServerRequest;
import com.data.SMS.dto.ServerResponse;
import com.data.SMS.model.Server;
import com.data.SMS.model.ServerLogs;
import com.data.SMS.repository.ServerRepository;
import com.data.SMS.repository.ServerLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements ServersService {

    private final ServerLogsRepository systemLogsRepository;

    @Autowired
    public ServerServiceImpl(ServerLogsRepository systemLogsRepository) {
        this.systemLogsRepository = systemLogsRepository;
    }
    @Autowired
    ServerRepository serverRepository;
    @Override
    public List<ServerResponse> getServers() {

       List<Server> server = serverRepository.findAll();
       List<ServerResponse> srList = new ArrayList<>();
       server.forEach( r -> {
           srList.add(new ServerResponse(r.getServerUrl()));
        });
       return srList;
    }

    @Override
    public ServerResponse saveServer(ServerRequest server) {
       Server serverModel =  serverRepository.save(new Server(server.getServerUrl()));
        if (serverModel.getId() <= 0) {
            return null;
        }
        return new ServerResponse(serverModel.getServerUrl());
    }

    @Override
    public ServerLogsResponse saveLogRecord(ServerLogsRequest slr) {
        ServerLogs systemLogs = null;
        ServerLogsResponse systemLogsResponse = null;

        if (slr != null) {
            systemLogs = new ServerLogs(slr.getAccessTime(), slr.getAccessStatus(), slr.getServerUrl(), slr.getErrorText(), slr.getStatusCode() );
            systemLogs = systemLogsRepository.save(systemLogs);
            if (systemLogs.getId() > 0) {
                systemLogsResponse = new ServerLogsResponse(systemLogs.getAccessTime(), systemLogs.getAccessStatus(), systemLogs.getServerUrl(), systemLogs.getErrorText(), systemLogs.getStatusCode());
            }
        }
        return systemLogsResponse;
    }

    @Override
    public List<ServerLogsResponse> getLogs() {
        List<ServerLogsResponse> slrList = null;
        try {
            List<ServerLogs>  serverLogsList = systemLogsRepository.findAll();
            slrList = new ArrayList<>();
            ServerLogsResponse slr = null;
            for (ServerLogs serverLogs : serverLogsList) {
                slr = new ServerLogsResponse(serverLogs.getAccessTime(), serverLogs.getAccessStatus(), serverLogs.getServerUrl(), serverLogs.getErrorText(), serverLogs.getStatusCode());
                slrList.add(slr);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return slrList;
    }

}
