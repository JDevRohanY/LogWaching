package com.JdevRohan.logWatcher.services;

import com.JdevRohan.logWatcher.models.LogEntry;
import com.JdevRohan.logWatcher.repositories.LogWriter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LogGeneratorServiceImpl implements LogGeneratorService{
    private final LogWriter logWriter;
    private final AtomicInteger logNumber = new AtomicInteger(1);

    public LogGeneratorServiceImpl(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    @Override
    public void writeLogs() {
        LogEntry logEntry = new LogEntry(LocalDateTime.now(), logNumber.getAndIncrement());
        logWriter.appendLogs(logEntry);
    }
}
