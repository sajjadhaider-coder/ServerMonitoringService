package com.data.SMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_logs")
public class ServerLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "ACCESS_TIME")
    private LocalDateTime accessTime;

    @Column(name = "ACCESS_STATUS")
    private String accessStatus;

    @Column(name = "SERVER_URL")
    private String serverUrl;

    @Column(name = "ERROR_TEXT")
    private String errorText;

    @Column(name = "STATUS_CODE")
    private String statusCode;

    public ServerLogs(LocalDateTime accessTime, String accessStatus, String serverUrl, String errorText, String statusCode) {
        this.accessTime = accessTime;
        this.accessStatus = accessStatus;
        this.serverUrl = serverUrl;
        this.errorText = errorText;
        this.statusCode = statusCode;
    }
}
