package com.cs348.backendservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Response object containing a list of available parking lots")
public class AvailableSpotResponse {

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(description = "Represents a parking lot containing multiple parking spots")
    public static class ParkingLot {
        @Getter
        @Setter
        @AllArgsConstructor
        @Schema(description = "Represents an individual parking spot within a lot")
        public static class ParkingSpot {
            @Schema(description = "Spot ID", example = "0")
            private int sid;

            @Schema(description = "Price per 30 minutes", example = "1.50")
            private float price;

            @Schema(description = "Type of parking", example = "visitor")
            private String parkingType;

            @Schema(description = "Availability of the parking spot", example = "true")
            private boolean isAvailable;
        }

        @Schema(description = "Lot ID", example = "0")
        private int lid;

        @Schema(description = "Lot name", example = "DC")
        private String lot_name;

        @Schema(description = "Number of likes for the lot", example = "20")
        private int likeNum;

        @Schema(description = "List of parking spots in the lot")
        public List<ParkingSpot> spots;
    }

    @Schema(description = "List of available parking lots")
    public List<ParkingLot> available_lots;

}


