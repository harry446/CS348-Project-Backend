package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.BookingHistoryRequest;
import com.cs348.backendservice.model.BookingHistoryResponse;
import com.cs348.backendservice.model.MakingBookingRequest;
import com.cs348.backendservice.repository.BookingHistory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingHistoryController {

    @Autowired
    private BookingHistory bookingHistory;

    @PostMapping("/bookingHistory")
    @Operation(summary = "Retrieve booking history", description = "Get booking history for a user, optionally sorted by price.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Information needed to retrieve past bookings",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BookingHistoryRequest.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "    \"uid\": 4,\n" +
                                    "    \"priceDesc\": true\n" +
                                    "}"))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking history retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingHistoryResponse.class),
                                    examples = @ExampleObject(value = "[\n" +
                                    "    {\n" +
                                    "        \"area\": \"UW\",\n" +
                                    "        \"address\": \"XXX\",\n" +
                                    "        \"lotName\": \"DC\",\n" +
                                    "        \"parkingType\": \"pay\",\n" +
                                    "        \"createTime\": \"2024-06-20T04:28:57.000+00:00\",\n" +
                                    "        \"startTime\": \"2024-06-15T19:30:00.000+00:00\",\n" +
                                    "        \"endTime\": \"2024-06-15T22:30:00.000+00:00\",\n" +
                                    "        \"price\": 15.0,\n" +
                                    "        \"status\": \"expired\"\n" +
                                    "    },\n" +
                                    "    {\n" +
                                    "        \"area\": \"UW\",\n" +
                                    "        \"address\": \"XXX\",\n" +
                                    "        \"lotName\": \"SCH\",\n" +
                                    "        \"parkingType\": \"pay\",\n" +
                                    "        \"createTime\": \"2024-06-20T04:28:57.000+00:00\",\n" +
                                    "        \"startTime\": \"2024-06-15T19:30:00.000+00:00\",\n" +
                                    "        \"endTime\": \"2024-06-15T20:30:00.000+00:00\",\n" +
                                    "        \"price\": 3.0,\n" +
                                    "        \"status\": \"cancelled\"\n" +
                                    "    }\n" +
                                    "]"))),
                    @ApiResponse(responseCode = "400", description = "Invalid request parameters")
            })
    public ResponseEntity<?> bookingHistoryHandler(@RequestBody BookingHistoryRequest historyRequest) {
        if (!historyRequest.isValid()) {
            return ResponseEntity.badRequest().body("Invalid request: Multiple sorting options selected.");
        }
        int uid = historyRequest.getUid();
        if (historyRequest.isPriceDesc()) {
            List<BookingHistoryResponse> res = bookingHistory.bookingHistory_priceDesc(uid);
            System.out.println("Booking history retrieved for user: " + uid + ", in descending order based on price");

            return new ResponseEntity(res, HttpStatus.OK);
        }
        else if (historyRequest.isPriceAsc()) {
            List<BookingHistoryResponse> res = bookingHistory.bookingHistory_priceAsc(uid);
            System.out.println("Booking history retrieved for user: " + uid + ", in ascending order based on price");

            return new ResponseEntity(res, HttpStatus.OK);
        }
        else {
            List<BookingHistoryResponse> res = bookingHistory.bookingHistory(uid);
            System.out.println("Booking history retrieved for user: " + uid);

            return new ResponseEntity(res, HttpStatus.OK);
        }

//        return new ResponseEntity("Booking history retrieved for user: " + uid, HttpStatus.OK);
    }

}
