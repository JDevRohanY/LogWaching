package com.JdevRohan.logWatcher.streamer;

import com.JdevRohan.logWatcher.services.LogReaderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogStreamerService {
    private final LogReaderService logReaderService;
    private final LogWebSocketHandler logWebSocketHandler;

    public LogStreamerService(LogReaderService logReaderService, LogWebSocketHandler logWebSocketHandler) {
        this.logReaderService = logReaderService;
        this.logWebSocketHandler = logWebSocketHandler;
    }

    //This is the watcher which is watching the new logs after every 1 sec
    @Scheduled(fixedRate = 1000)
    public void watchLogs(){
        List<String> newLogs = logReaderService.getLatestLogs();
        for(String log : newLogs){
            logWebSocketHandler.broadcast(log);
        }
    }
}
