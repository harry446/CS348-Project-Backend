package com.cs348.backendservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class AvailableSpotResponse {

    public static class ParkingSpot {
        private String sid;
        private String price;
        private String parkingType;
        private boolean isAvailable;
    }

    public static class ParkingLot {
        private String lid;
        private String likeNum;
        private List<ParkingSpot> spots;
    }

    private List<ParkingLot> available_lots;

}
