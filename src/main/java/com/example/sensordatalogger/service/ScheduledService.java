package com.example.sensordatalogger.service;

import com.example.sensordatalogger.repository.CurrentLogRepository;
import com.example.sensordatalogger.repository.VoltageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ScheduledService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledService.class);
    private final int DELETE_ALL_INTERVAL_MS = 14 * 60 * 1000;

    @Autowired
    VoltageLogRepository voltageLogRepository;
    @Autowired
    CurrentLogRepository currentLogRepository;

    @Scheduled(fixedRate = DELETE_ALL_INTERVAL_MS) // Execute every (DELETE_ALL_INTERVAL_MS /1000) seconds
//    @Scheduled(cron = "0 0 0 * * ?") // Execute every day at midnight
    public void deleteAllLogs() {
        log.info("Deleting all documents...");
//        voltageLogRepository.deleteAll();
//        currentLogRepository.deleteAll();
        log.info("Documents correctly deleted");
    }
}
