package com.example.sensordatalogger.repository;

import com.example.sensordatalogger.model.CurrentLog;
import com.example.sensordatalogger.model.VoltageLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VoltageLogRepository extends MongoRepository<VoltageLog, String> {

    Optional<CurrentLog> findFirstByOrderByDateTimeDesc();
}
