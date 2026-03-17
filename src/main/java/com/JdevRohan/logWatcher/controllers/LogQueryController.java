package com.JdevRohan.logWatcher.controllers;

import com.JdevRohan.logWatcher.repositories.LogReader;
import com.JdevRohan.logWatcher.services.LogReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogQueryController {
    private final LogReaderService logReaderService;

    public LogQueryController(LogReaderService logReaderService) {
        this.logReaderService = logReaderService;
    }


    // Instead of returning List<String>, we can make it to return the dto's
    @GetMapping("/lasts")
    public List<String> getLastNLines(@RequestParam(defaultValue = "10") int n){
        return logReaderService.getLastNLines(n);
    }


//    //Just for testing purpose!!
//    @GetMapping("/new")
//    public List<String> getNewLogs() {
//        return logReaderService.getLatestLogs();
//    }
}
