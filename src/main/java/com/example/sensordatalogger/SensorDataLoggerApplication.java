package com.example.sensordatalogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SensorDataLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorDataLoggerApplication.class, args);
	}

}
