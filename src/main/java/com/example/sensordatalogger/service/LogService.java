package com.example.sensordatalogger.service;

import com.example.sensordatalogger.model.CurrentLog;
import com.example.sensordatalogger.model.InputLog;
import com.example.sensordatalogger.model.AbstractLog;
import com.example.sensordatalogger.model.VoltageLog;
import com.example.sensordatalogger.repository.CurrentLogRepository;
import com.example.sensordatalogger.repository.VoltageLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    VoltageLogRepository voltageLogRepository;
    @Autowired
    CurrentLogRepository currentLogRepository;

    public AbstractLog createLog(InputLog inputLog) {
        logger.info("Creating log entry...");

        AbstractLog abstractLog;
        if (inputLog.getMeasureType().equalsIgnoreCase("CURRENT")) {
            abstractLog = currentLogRepository.save(CurrentLog.fromInputLog(inputLog));
        } else if (inputLog.getMeasureType().equalsIgnoreCase("VOLTAGE")) {
            abstractLog = voltageLogRepository.save(VoltageLog.fromInputLog(inputLog));
        }
        else {throw new RuntimeException("Measure type is not valid");}
        logger.info("Log entry successfully created");

        return abstractLog;
    }

    public AbstractLog getMostRecentMeasure(String measureType) {
        AbstractLog abstractLog;
        if (measureType.equalsIgnoreCase("CURRENT")) {
            abstractLog = currentLogRepository.findFirstByOrderByDateTimeDesc().orElse(new CurrentLog());
        } else if (measureType.equalsIgnoreCase("VOLTAGE")) {
            abstractLog = voltageLogRepository.findFirstByOrderByDateTimeDesc().orElse(new CurrentLog());
        }
        else {throw new RuntimeException("Measure type is not valid");}
        return abstractLog;
    }
}
