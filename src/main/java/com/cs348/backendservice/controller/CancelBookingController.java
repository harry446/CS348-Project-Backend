package com.cs348.backendservice.controller;

import com.cs348.backendservice.repository.CancelBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CancelBookingController {

    @Autowired
    private CancelBooking cancelBooking;

    @DeleteMapping("/cancelBooking")
    public ResponseEntity cancelBookingHandler(@RequestParam int uid, @RequestParam int bid) {
        cancelBooking.cancelBooking(uid, bid);

        return new ResponseEntity("Booking canceled for user: " + uid + ", with bid: " + bid, HttpStatus.OK);
    }

}
