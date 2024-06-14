package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.BookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @PostMapping("/makeBooking")
    public ResponseEntity<String> makeBooking(@RequestBody BookingRequest bookingRequest) {
        System.out.println("START HOUR: " + bookingRequest.getStartHour());
        System.out.println("END HOUR: " + bookingRequest.getEndHour());
        return ResponseEntity.ok("Booking created for user " + bookingRequest.getUid() + " at " + bookingRequest.getLid());
    }
}
