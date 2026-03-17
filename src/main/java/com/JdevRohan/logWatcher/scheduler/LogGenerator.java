package com.JdevRohan.logWatcher.scheduler;

import com.JdevRohan.logWatcher.services.LogGeneratorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogGenerator {
    private final LogGeneratorService logGeneratorService;

    public LogGenerator(LogGeneratorService logGeneratorService) {
        this.logGeneratorService = logGeneratorService;
    }

    @Scheduled(fixedRate = 1000)
    public void generateLogs(){
        logGeneratorService.writeLogs();
    }
}
