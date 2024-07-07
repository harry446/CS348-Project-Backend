package com.cs348.backendservice.controller;

import com.cs348.backendservice.exceptions.MaximumBookingExceededException;
import com.cs348.backendservice.exceptions.UserNotFoundException;
import com.cs348.backendservice.model.AvailableSpotRequest;
import com.cs348.backendservice.model.AvailableSpotResponse;
import com.cs348.backendservice.service.AvailableSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableSpotsController {
    @Autowired
    private AvailableSpotService availableSpotsService;

    @PostMapping("/listAvailableSpots")
    public ResponseEntity<?> availableSpotsHandler(@RequestBody AvailableSpotRequest spotsRequest) {

        int uid = spotsRequest.getUid();
        try {
            AvailableSpotResponse res = availableSpotsService.listAvailableSpots(uid);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (UserNotFoundException notFoundException) {
            return new ResponseEntity(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (MaximumBookingExceededException maximumExceededException) {
            return new ResponseEntity(maximumExceededException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
