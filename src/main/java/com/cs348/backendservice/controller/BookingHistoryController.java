package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.BookingHistoryRequest;
import com.cs348.backendservice.model.BookingHistoryResponse;
import com.cs348.backendservice.repository.BookingHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingHistoryController {

    @Autowired
    private BookingHistory bookingHistory;

    @GetMapping("/bookingHistory")
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
