package com.cs348.backendservice.controller;

import com.cs348.backendservice.repository.CancelBooking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CancelBookingController {

    @Autowired
    private CancelBooking cancelBooking;

    @DeleteMapping("/cancelBooking")
    @Operation(summary = "Cancel a booking", description = "Cancel an existing booking and decrement the booking_num appropriately.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking canceled successfully",
                            content = @Content(mediaType ="String", examples = @ExampleObject(value = "Booking canceled for user 2, with bid: 8"))),
            })
    public ResponseEntity<String> cancelBookingHandler(@RequestParam int uid, @RequestParam int bid) {
        cancelBooking.cancelBooking(uid, bid);

        return new ResponseEntity("Booking canceled for user: " + uid + ", with bid: " + bid, HttpStatus.OK);
    }

}
