package com.cs348.backendservice.service;

import com.cs348.backendservice.exceptions.MaximumBookingExceededException;
import com.cs348.backendservice.model.AvailableSpotResponse;
import com.cs348.backendservice.repository.AvailableSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AvailableSpotService {
    @Autowired
    private AvailableSpot availableSpots;
    public AvailableSpotResponse listAvailableSpots (int uid, String location, String startTime, String endTime, float duration, boolean freeOnly) throws Exception {
        if (availableSpots.getCurBookingCount(uid) >= 3) {     // == 3 is enough
            throw new MaximumBookingExceededException("User exceeded maximum booking limit, UID: " + uid);
        }

        String parking_type = freeOnly ? "pay" : "random garbage value";
        List<AvailableSpotResponse.ParkingLot> res = availableSpots.getAvailable(uid, location, startTime, endTime, duration, parking_type);

        return new AvailableSpotResponse(res);
//        availableSpots.getAll();




//        List<AvailableSpotResponse.ParkingLot.ParkingSpot> spot = Arrays.asList(new AvailableSpotResponse.ParkingLot.ParkingSpot("a", "b", "c", true));
//        List<AvailableSpotResponse.ParkingLot> lot = Arrays.asList(new AvailableSpotResponse.ParkingLot("lid", "likeNum", spot));
//        return new AvailableSpotResponse(lot);
    }
}
