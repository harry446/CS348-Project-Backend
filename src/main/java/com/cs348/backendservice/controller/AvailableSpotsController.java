package com.cs348.backendservice.controller;

import com.cs348.backendservice.exceptions.MaximumBookingExceededException;
import com.cs348.backendservice.exceptions.UserNotFoundException;
import com.cs348.backendservice.model.AvailableSpotRequest;
import com.cs348.backendservice.model.AvailableSpotResponse;
import com.cs348.backendservice.model.BookingHistoryRequest;
import com.cs348.backendservice.model.BookingHistoryResponse;
import com.cs348.backendservice.service.AvailableSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableSpotsController {
    @Autowired
    private AvailableSpotService availableSpotsService;

    @PostMapping("/listAvailableSpots")
    @Operation(summary = "Retrieve available spots", description = "Get available spots based on user requirements.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Information needed to list available spots",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = AvailableSpotRequest.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"uid\": 2,\n" +
                                    "  \"location\": \"MC\",\n" +
                                    "  \"freeOnly\": false,\n" +
                                    "  \"startYear\": \"2024\",\n" +
                                    "  \"startMonth\": \"06\",\n" +
                                    "  \"startDate\": \"14\",\n" +
                                    "  \"startHour\": \"11\",\n" +
                                    "  \"startMinute\": \"30\",\n" +
                                    "  \"endYear\": \"2024\",\n" +
                                    "  \"endMonth\": \"06\",\n" +
                                    "  \"endDate\": \"14\",\n" +
                                    "  \"endHour\": \"14\",\n" +
                                    "  \"endMinute\": \"00\"\n" +
                                    "}"))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Available spots listed successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AvailableSpotResponse.class))),
                    @ApiResponse(responseCode = "406", description = "User maximum booking exceeded"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<?> availableSpotsHandler(@RequestBody AvailableSpotRequest spotsRequest) {

        int uid = spotsRequest.getUid();
        try {
            AvailableSpotResponse res = availableSpotsService.listAvailableSpots(uid);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (UserNotFoundException notFoundException) {
            return new ResponseEntity(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (MaximumBookingExceededException maximumExceededException) {
            return new ResponseEntity(maximumExceededException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}