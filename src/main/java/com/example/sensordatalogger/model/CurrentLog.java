package com.example.sensordatalogger.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "current-measures")
public class CurrentLog extends AbstractLog{

    public static CurrentLog fromInputLog(InputLog inputLog) {
        CurrentLog currentLog = new CurrentLog();
        currentLog.setValue(inputLog.getValue());
        currentLog.setDateTime(currentLog.getCurrentDateForRomeTimezone());

        return  currentLog;
    }
}
