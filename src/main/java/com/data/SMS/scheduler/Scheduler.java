package com.data.SMS.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final ServerMonitor bgrs;

    // Spring will inject the BallGameResultScraper instance
    public Scheduler(ServerMonitor bgrs) {
        this.bgrs = bgrs;
    }

    @Scheduled(fixedRate = 300000) // Runs every 5 minutes (300000 ms)
    public void scheduleTask() {
        bgrs.run(); // Calls the run() method of BallGameResultScraper
    }
}