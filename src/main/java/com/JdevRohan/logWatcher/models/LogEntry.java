package com.JdevRohan.logWatcher.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogEntry {
    private LocalDateTime timeStamp;
    private int logNumber;

    public LogEntry(LocalDateTime timeStamp, int logNumber) {
        this.timeStamp = timeStamp;
        this.logNumber = logNumber;
    }

    @Override
    public String toString() {
        return this.timeStamp + " | log Number : " + this.logNumber;
    }
}
