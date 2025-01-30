package com.data.SMS.repository;

import com.data.SMS.model.ServerLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerLogsRepository extends JpaRepository<ServerLogs, Long> {
}
