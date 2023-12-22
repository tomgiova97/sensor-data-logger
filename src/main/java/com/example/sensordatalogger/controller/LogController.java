package com.example.sensordatalogger.controller;

import com.example.sensordatalogger.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sensordatalogger.service.LogService;

import java.util.Date;

@RestController
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping
    public Log createLog(@RequestBody Log log) {
        return logService.createLog(log);
    }

    @GetMapping("/currentDate")
    public String getCurrentDate() {
        return Long.toString(new Date().getTime());
    }
}
