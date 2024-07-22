package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.MakingBookingRequest;
import com.cs348.backendservice.repository.MakeBooking;
import com.cs348.backendservice.tools.Tools;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MakeBookingController {

//    final double PRICE = 1.50;      // TODO: get price according to sid

    @Autowired
    private MakeBooking makeBooking;

    @Autowired
    private Tools tools;

    @PostMapping("/makeBooking")
    @Operation(summary = "Create a booking", description = "Make a booking and update the booking count in the users table.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Booking information needed to create a booking",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = MakingBookingRequest.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"uid\": 2,\n" +
                                    "    \"lid\": 1,\n" +
                                    "    \"sid\": 3,\n" +
                                    "    \"price\": 1.5,\n" +
                                    "    \"startYear\": \"2024\",\n" +
                                    "    \"startMonth\": \"06\",\n" +
                                    "    \"startDate\": \"14\",\n" +
                                    "    \"startHour\": \"10\",\n" +
                                    "    \"startMinute\": \"30\",\n" +
                                    "    \"endYear\": \"2024\",\n" +
                                    "    \"endMonth\": \"06\",\n" +
                                    "    \"endDate\": \"14\",\n" +
                                    "    \"endHour\": \"14\",\n" +
                                    "    \"endMinute\": \"00\"\n" +
                                    "}"))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking created successfully",
                            content = @Content(mediaType ="String", examples = @ExampleObject(value = "Booking created for user 2 at lot 1 and spot 3. From 2024-06-14 10:30:00 to 2024-06-14 14:00:00"))),
            })
    public ResponseEntity<String> makeBookingHandler(@RequestBody MakingBookingRequest bookingRequest) {
        String start_time = tools.convertToTime(bookingRequest.getStartYear(), bookingRequest.getStartMonth(), bookingRequest.getStartDate(),
                bookingRequest.getStartHour(), bookingRequest.getStartMinute());

        String end_time = tools.convertToTime(bookingRequest.getEndYear(), bookingRequest.getEndMonth(), bookingRequest.getEndDate(),
                bookingRequest.getEndHour(), bookingRequest.getEndMinute());

        makeBooking.insertRow(bookingRequest.getUid(), bookingRequest.getLid(), bookingRequest.getSid(),
                start_time, end_time, bookingRequest.getPrice());

        return new ResponseEntity("Booking created for user " + bookingRequest.getUid() + " at lot " + bookingRequest.getLid() +
                " and spot " + bookingRequest.getSid() + ". From " + start_time + " to " + end_time
                , HttpStatus.OK);
    }

}


