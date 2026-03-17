package com.JdevRohan.logWatcher.repositories;

import com.JdevRohan.logWatcher.exceptions.FileWriterException;
import com.JdevRohan.logWatcher.models.LogEntry;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;

@Repository
public class FileLogWriter implements LogWriter {
    //Functionality of this is to write the log to the file
    private final String FILE_PATH = "logs.txt";
    @Override
    public void appendLogs(LogEntry logEntry) {
        try(FileWriter file = new FileWriter(FILE_PATH, true)){
            file.write(logEntry.toString());
            file.write('\n');
            System.out.println(logEntry.toString());
        }catch (Exception e){
            throw new FileWriterException("Unable to write the log to the file" + FileLogWriter.class, e);
        }
    }
}
