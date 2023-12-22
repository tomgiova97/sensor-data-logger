package com.example.sensordatalogger.service;

import com.example.sensordatalogger.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sensordatalogger.repository.LogRepository;

import java.util.Date;

@Service
public class LogService {
    @Autowired
    LogRepository logRepository;

    public Log createLog(Log log) {
        Log savedLog = logRepository.save(log);
        Date logDateConvertedToRomeTimezone = new Date(savedLog.getDateTime().getTime() + 60*60*1000); //Adding one hour to the UTC time
        savedLog.setDateTime(logDateConvertedToRomeTimezone);
        return savedLog;
    }

}
