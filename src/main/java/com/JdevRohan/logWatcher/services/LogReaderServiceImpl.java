package com.JdevRohan.logWatcher.services;

import com.JdevRohan.logWatcher.repositories.LogReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogReaderServiceImpl implements LogReaderService{
    private final LogReader logReader;

    public LogReaderServiceImpl(LogReader logReader) {
        this.logReader = logReader;
    }

    @Override
    public List<String> getLastNLines(int n) {
        return logReader.readLastNLines(n);
    }

    @Override
    public List<String> getLatestLogs() {
        return logReader.readNewLogs();
    }
}
