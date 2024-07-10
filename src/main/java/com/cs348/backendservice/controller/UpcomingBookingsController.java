package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.BookingHistoryRequest;
import com.cs348.backendservice.model.BookingHistoryResponse;
import com.cs348.backendservice.model.UpcomingBookingsResponse;
import com.cs348.backendservice.repository.BookingHistory;
import com.cs348.backendservice.repository.UpcomingBookings;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UpcomingBookingsController {

    @Autowired
    private UpcomingBookings upcomingBookings;

    @PostMapping("/bookingHistory")
    @Operation(summary = "Retrieve booking history", description = "Get booking history for a user, optionally sorted by price.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Upcoming booking history retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UpcomingBookingsResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request parameters")
            })
    public ResponseEntity<?> UpcomingBookingsHandler(@RequestParam int uid) {

        List<UpcomingBookingsResponse> res = upcomingBookings.upcomingBookings(uid);
        System.out.println("Booking history retrieved for user: " + uid + ", upcoming only");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
