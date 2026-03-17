package com.JdevRohan.logWatcher.services;

import java.util.List;

public interface LogReaderService {
    List<String> getLastNLines(int n);

    List<String> getLatestLogs();
}
