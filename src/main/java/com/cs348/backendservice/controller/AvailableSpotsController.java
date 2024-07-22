package com.cs348.backendservice.controller;

import com.cs348.backendservice.exceptions.MaximumBookingExceededException;
import com.cs348.backendservice.exceptions.UserNotFoundException;
import com.cs348.backendservice.model.AvailableSpotRequest;
import com.cs348.backendservice.model.AvailableSpotResponse;
import com.cs348.backendservice.model.BookingHistoryRequest;
import com.cs348.backendservice.model.BookingHistoryResponse;
import com.cs348.backendservice.service.AvailableSpotService;
import com.cs348.backendservice.tools.Tools;
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

import java.util.List;

@RestController
public class AvailableSpotsController {
    @Autowired
    private AvailableSpotService availableSpotsService;

    @Autowired
    private Tools tools;

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
        List<String> location = spotsRequest.getLocation();
        String startTime = tools.convertToTime(spotsRequest.getStartYear(), spotsRequest.getStartMonth(), spotsRequest.getStartDate(),
                spotsRequest.getStartHour(), spotsRequest.getStartMinute());
        String endTime = tools.convertToTime(spotsRequest.getEndYear(), spotsRequest.getEndMonth(), spotsRequest.getEndDate(),
                spotsRequest.getEndHour(), spotsRequest.getEndMinute());
        float duration = tools.convertDuration(spotsRequest.getStartYear(), spotsRequest.getStartMonth(), spotsRequest.getStartDate(),
                spotsRequest.getStartHour(), spotsRequest.getStartMinute(),
                spotsRequest.getEndYear(), spotsRequest.getEndMonth(), spotsRequest.getEndDate(),
                spotsRequest.getEndHour(), spotsRequest.getEndMinute());
        boolean isFreeOnly = spotsRequest.isFreeOnly();

        try {
            AvailableSpotResponse res = availableSpotsService.listAvailableSpots(uid, location, startTime, endTime, duration, isFreeOnly);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (UserNotFoundException notFoundException) {
            return new ResponseEntity(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (MaximumBookingExceededException maximumExceededException) {
            return new ResponseEntity(maximumExceededException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
