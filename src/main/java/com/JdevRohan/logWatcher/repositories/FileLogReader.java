package com.JdevRohan.logWatcher.repositories;

import com.JdevRohan.logWatcher.exceptions.FileLogReaderException;
import org.springframework.stereotype.Repository;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class FileLogReader implements LogReader {
    private final String FILE_PATH = "logs.txt";
    private long offset = 0;

    @Override
    public List<String> readLastNLines(int n) {
        List<String> lastLines = new LinkedList<>();
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r")) {
            long fileLength = file.length();
            long pointer = fileLength - 1;

            int lineCount = 0;
            StringBuilder lineBuilder = new StringBuilder();
            while (pointer > 0 && lineCount < n) {
                file.seek(pointer);
                char c = (char) file.read();

                if(c == '\n'){
                    if(!lineBuilder.isEmpty()){
                        lastLines.add(0, lineBuilder.reverse().toString());
                        lineCount++;
                        lineBuilder.setLength(0);
                    }
                }else{
                    lineBuilder.append(c);
                }
                pointer--;
            }
            return lastLines;
        } catch (Exception e) {
            throw new FileLogReaderException("Unable to read the last " + n + " lines of the log file. " + FileLogReaderException.class, e);
        }
    }

    @Override
    public List<String> readNewLogs() {
        List<String> newLines = new ArrayList<>();
        try(RandomAccessFile file = new RandomAccessFile(FILE_PATH, "r")){
            long fileLength = file.length();
            //To check if the file is truncated or not, compare with offset
            if(offset> fileLength){
                //it means file is changed or truncated, reset the offset
                offset = 0;
            }
            file.seek(offset);
            String line;
            while((line = file.readLine())!= null){
                newLines.add(line);
            }
            //change the offset to last line
            offset = fileLength;
            return newLines;
        }catch (Exception e){
            throw new FileLogReaderException("Unable to read the new logs from the file." + FileLogReaderException.class, e);
        }
    }
}


/*
2026-03-17T16:35:15.919704900 | log Number : 6
2026-03-17T16:35:16.932361500 | log Number : 7
2026-03-17T16:35:17.921697800 | log Number : 8
 */