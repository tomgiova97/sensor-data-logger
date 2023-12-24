package com.example.sensordatalogger.service;

import com.example.sensordatalogger.model.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sensordatalogger.repository.LogRepository;

import java.util.Date;

@Service
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    @Autowired
    LogRepository logRepository;

    public Log createLog(Log log) {
        logger.info("Creating log entry...");
        Date currentdate = new Date();
        Date currentDateConvertedToRomeTimezone = new Date(currentdate.getTime() + 60*60*1000); //Adding one hour to the UTC time
        log.setDateTime(currentDateConvertedToRomeTimezone);

        logger.info("Log entry successfully created");

        return logRepository.save(log);
    }

}
