package com.cs348.backendservice.service;

import com.cs348.backendservice.exceptions.MaximumBookingExceededException;
import com.cs348.backendservice.model.AvailableSpotResponse;
import com.cs348.backendservice.repository.AvailableSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AvailableSpotService {
    @Autowired
    private AvailableSpot availableSpots;
    public AvailableSpotResponse listAvailableSpots (int uid, List<String> location, String startTime, String endTime, float duration, boolean freeOnly) throws Exception {
        availableSpots.trivialUpdate(uid);

        if (availableSpots.getCurBookingCount(uid) >= 3) {     // == 3 is enough
            throw new MaximumBookingExceededException("User exceeded maximum booking limit, UID: " + uid);
        }

        String parking_type = freeOnly ? "pay" : "random garbage value";

        List<AvailableSpotResponse.ParkingLot> lots = new ArrayList<>();
        for (String loc : location) {
            List<Object[]> res1 = availableSpots.getAvailable(uid, loc, startTime, endTime, duration, parking_type);

            for (Object[] k : res1) {
                if (lots.isEmpty()) {
                    List<AvailableSpotResponse.ParkingLot.ParkingSpot> s = new ArrayList<>();
                    AvailableSpotResponse.ParkingLot l = new AvailableSpotResponse.ParkingLot((int) k[0], (String) k[5], (int) k[1], s);
                    lots.add(l);
                }

                if (lots.get(lots.size()-1).getLid() != (int) k[0]) {       // new lot number
                    List<AvailableSpotResponse.ParkingLot.ParkingSpot> s = new ArrayList<>();
                    AvailableSpotResponse.ParkingLot l = new AvailableSpotResponse.ParkingLot((int) k[0], (String) k[5], (int) k[1], s);
                    lots.add(l);
                }

                AvailableSpotResponse.ParkingLot.ParkingSpot s = new AvailableSpotResponse.ParkingLot.ParkingSpot((int) k[2], (float) k[3], (String) k[4], true);
                lots.get(lots.size()-1).spots.add(s);
            }

            List<Object[]> res2 = availableSpots.getAll(loc);
            for (Object[] k : res2) {
                for (AvailableSpotResponse.ParkingLot l : lots) {
                    if (l.getLid() != (int) k[0]) {
                        continue;
                    }
                    boolean flag = false;
                    for (AvailableSpotResponse.ParkingLot.ParkingSpot s : l.spots) {
                        if (s.getSid() == (int) k[2]) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        AvailableSpotResponse.ParkingLot.ParkingSpot s = new AvailableSpotResponse.ParkingLot.ParkingSpot((int) k[2], (float) k[3], (String) k[4], false);
                        l.spots.add(s);
                    }
                    break;
                }
            }
        }

        return new AvailableSpotResponse(lots);
    }
}
