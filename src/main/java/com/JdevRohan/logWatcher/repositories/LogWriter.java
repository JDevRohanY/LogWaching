package com.JdevRohan.logWatcher.repositories;

import com.JdevRohan.logWatcher.models.LogEntry;

public interface LogWriter {
    void appendLogs(LogEntry logEntry);
}
