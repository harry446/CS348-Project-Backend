package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.BookingHistoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingHistoryController {

    @GetMapping("/bookingHistory")
    public ResponseEntity bookingHistoryHandler(@RequestBody BookingHistoryRequest historyRequest) {
        int uid = Integer.parseInt(historyRequest.getUid());
        if (historyRequest.isPriceDesc()) {
            bookingHistory_priceDesc(uid);
            return new ResponseEntity("Booking history retrieved for user: " + uid + ", in descending order based on price", HttpStatus.OK);
        }
        else {
            bookingHistory(uid);
            return new ResponseEntity("Booking history retrieved for user: " + uid, HttpStatus.OK);
        }

//        return new ResponseEntity("Booking history retrieved for user: " + uid, HttpStatus.OK);
    }

    public static void bookingHistory(int uid) {
        System.out.println("booking history!");
    }

    public static void bookingHistory_priceDesc(int uid) {
        System.out.println("booking history sorted in descending order based on price!");
    }
}
