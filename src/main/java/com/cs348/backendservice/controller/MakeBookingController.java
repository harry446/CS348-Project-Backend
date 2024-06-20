package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.MakingBookingRequest;
import com.cs348.backendservice.repository.MakeBooking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MakeBookingController {

    final double PRICE = 1.50;      // TODO: get price according to sid

    @Autowired
    private MakeBooking makeBooking;

    @PostMapping("/makeBooking")
    public ResponseEntity makeBookingHandler(@RequestBody MakingBookingRequest bookingRequest) {
        String start_time = convertToTime(bookingRequest.getStartYear(), bookingRequest.getStartMonth(), bookingRequest.getStartDate(),
                bookingRequest.getStartHour(), bookingRequest.getStartMinute());

        String end_time = convertToTime(bookingRequest.getEndYear(), bookingRequest.getEndMonth(), bookingRequest.getEndDate(),
                bookingRequest.getEndHour(), bookingRequest.getEndMinute());

        makeBooking.insertRow(bookingRequest.getUid(), bookingRequest.getLid(), bookingRequest.getSid(),
                start_time, end_time, PRICE);

        return new ResponseEntity("Booking created for user " + bookingRequest.getUid() + " at lot " + bookingRequest.getLid() +
                " and spot " + bookingRequest.getSid() + ". From " + start_time + " to " + end_time
                , HttpStatus.OK);
    }

    public String convertToTime(String startYear, String startMonth, String startDate, String startHour, String startMinute) {
        return startYear + "-" + startMonth + "-" + startDate + " " + startHour + ":" + startMinute + ":00";
    }
}


