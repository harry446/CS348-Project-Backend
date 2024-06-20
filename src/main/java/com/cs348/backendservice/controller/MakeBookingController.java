package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.MakingBookingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MakeBookingController {

    final double PRICE = 1.50;      // TODO: get price according to sid
    @PostMapping("/makeBooking")
    public ResponseEntity makeBooking(@RequestBody MakingBookingRequest bookingRequest) {
        System.out.println("START HOUR: " + bookingRequest.getStartHour());
        System.out.println("END HOUR: " + bookingRequest.getEndHour());

        String start_time = convertToTime(bookingRequest.getStartYear(), bookingRequest.getStartMonth(), bookingRequest.getStartDate(),
                bookingRequest.getStartHour(), bookingRequest.getStartMinute());

        String end_time = convertToTime(bookingRequest.getEndYear(), bookingRequest.getEndMonth(), bookingRequest.getEndDate(),
                bookingRequest.getEndHour(), bookingRequest.getEndMinute());

        makeBooking(Integer.parseInt(bookingRequest.getUid()), Integer.parseInt(bookingRequest.getLid()), Integer.parseInt(bookingRequest.getSid()),
                start_time, end_time, PRICE, 1);

        return new ResponseEntity("Booking created for user " + bookingRequest.getUid() + " at " + bookingRequest.getLid() +
                ". From " + start_time + " to " + end_time
                , HttpStatus.OK);
    }

    public String convertToTime(String startYear, String startMonth, String startDate, String startHour, String startMinute) {
        return startYear + "-" + startMonth + "-" + startDate + " " + startHour + ":" + startMinute + ":00";
    }

    public static void makeBooking(int uid, int lid, int sid,
                                 String start_time, String end_time, double price, int status) {
        System.out.println("hi");
        return;
    }
}


