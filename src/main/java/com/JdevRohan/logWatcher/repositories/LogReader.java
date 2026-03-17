package com.JdevRohan.logWatcher.repositories;

import java.util.List;

public interface LogReader {
    List<String> readLastNLines(int n);

    List<String> readNewLogs();
}
