package com.example.sensordatalogger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensor-data-main")
public class Log {
    @Id
    private String id;
    private Double value;
    @Field(targetType = FieldType.TIMESTAMP)
    private Date dateTime;

}
