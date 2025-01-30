package com.data.SMS.service;

import com.data.SMS.dto.ServerLogsRequest;
import com.data.SMS.dto.ServerLogsResponse;
import com.data.SMS.dto.ServerRequest;
import com.data.SMS.dto.ServerResponse;

import java.util.List;

public interface ServersService {
    List<ServerResponse> getServers();
    ServerResponse saveServer(ServerRequest serverRequest);
    List<ServerLogsResponse> getLogs();
    ServerLogsResponse saveLogRecord(ServerLogsRequest systemLogs);
}
