package com.cs348.backendservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class UpcomingBookingsResponse {
    private String area;

    @Schema(description = "The address of the parking lot", example = "XXX")
    private String address;

    @Schema(description = "The name of the parking lot", example = "DC")
    private String lotName;

    @Schema(description = "Type of parking spot", example = "pay")
    private String parkingType;

    @Schema(description = "Timestamp when the booking was created", example = "2024-06-20T04:28:57.000+00:00")
    private Timestamp createTime;

    @Schema(description = "Start time of the booking", example = "2024-06-15T19:30:00.000+00:00")
    private Timestamp startTime;

    @Schema(description = "End time of the booking", example = "2024-06-15T22:30:00.000+00:00")
    private Timestamp endTime;

    @Schema(description = "Price of the booking", example = "15.0")
    private float price;

    @Schema(description = "Status of the booking (e.g., expired, booked, cancelled)", example = "expired")
    private String status;

    @Schema(description = "Indicates whether the user has liked this parking spot", example = "true")
    private boolean liked;

    @Schema(description = "Booking id of the booking", example = "2")
    private int bid;

    @Schema(description = "uid of the user that made the booking", example = "2")
    private int uid;
}