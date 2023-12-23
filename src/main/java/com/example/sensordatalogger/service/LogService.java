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
        Date currentdate = new Date();
        Date currentDateConvertedToRomeTimezone = new Date(currentdate.getTime() + 60*60*1000); //Adding one hour to the UTC time
        log.setDateTime(currentDateConvertedToRomeTimezone);

        return logRepository.save(log);
    }

}
