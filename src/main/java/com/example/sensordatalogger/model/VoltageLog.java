package com.example.sensordatalogger.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voltage-measures")
public class VoltageLog extends AbstractLog{

    public static VoltageLog fromInputLog(InputLog inputLog) {
        VoltageLog voltageLog = new VoltageLog();
        voltageLog.setValue(inputLog.getValue());
        voltageLog.setDateTime(voltageLog.getCurrentDateForRomeTimezone());

        return voltageLog;
    }
}
