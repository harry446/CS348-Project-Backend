package com.cs348.backendservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CancelBookingController {

    @DeleteMapping("/cancelBooking")
    public ResponseEntity cancelBookingHandler(@RequestParam int uid, @RequestParam int bid) {
        cancelBooking(uid, bid);

        return new ResponseEntity("Booking canceled for user: " + uid + ", with bid: " + bid, HttpStatus.OK);
    }

    public static void cancelBooking(int uid, int bid) {
        System.out.println("cancel booking!");
    }

}
