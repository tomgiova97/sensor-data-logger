package com.example.sensordatalogger.repository;

import com.example.sensordatalogger.model.CurrentLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CurrentLogRepository extends MongoRepository<CurrentLog, String> {

    Optional<CurrentLog> findFirstByOrderByDateTimeDesc();
}
