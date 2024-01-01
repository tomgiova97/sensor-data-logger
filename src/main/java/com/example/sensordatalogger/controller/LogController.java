package com.example.sensordatalogger.controller;

import com.example.sensordatalogger.model.InputLog;
import com.example.sensordatalogger.model.AbstractLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sensordatalogger.service.LogService;

@RestController
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping
    public AbstractLog createLog(@RequestBody InputLog log) {
        return logService.createLog(log);
    }

    @GetMapping("/{measureType}")
    public AbstractLog getMostRecentMeasure(@PathVariable String measureType) {
        return logService.getMostRecentMeasure(measureType);
    }

}
