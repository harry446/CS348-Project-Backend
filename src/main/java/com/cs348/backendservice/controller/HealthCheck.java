package com.cs348.backendservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
public class HealthCheck {

    @GetMapping("/healthCheck")
    public String healthCheck() {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formattedDateTime = utcNow.format(formatter);
        return "CS348 service is up and running! " + formattedDateTime;
    }
}
