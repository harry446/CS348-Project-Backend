package com.cs348.backendservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
//@Entity
//@Table(name = "booking_history")
public class BookingHistoryResponse {
    @Schema(description = "The area where the parking lot is located", example = "UW")
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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "area")
//    private String area;
//
//    @Column(name = "address")
//    private String address;
//
//    @Column(name = "lot_name")
//    private String lotName;
//
//    @Column(name = "parking_type")
//    private String parkingType;
//
//    @Column(name = "create_time")
//    private Timestamp createTime;
//
//    @Column(name = "start_time")
//    private Timestamp startTime;
//
//    @Column(name = "end_time")
//    private Timestamp endTime;
//
//    @Column(name = "price")
//    private float price;
//
//    @Column(name = "status")
//    private String status;
}
