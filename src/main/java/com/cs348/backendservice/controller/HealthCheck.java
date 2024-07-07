package com.cs348.backendservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
public class HealthCheck {

    @GetMapping("/healthCheck")
    @Operation(summary = "Health check", description = "Check the health of the service.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "service is up and running",
                    content = @Content(mediaType = "String", examples = @ExampleObject(value = "CS348 service is up and running!")))
            })
    public String healthCheck() {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formattedDateTime = utcNow.format(formatter);
        return "CS348 service is up and running! " + formattedDateTime;
    }
}
