package com.cs348.backendservice.model;

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
    private String area;
    private String address;
    private String lotName;
    private String parkingType;
    private Timestamp createTime;
    private Timestamp startTime;
    private Timestamp endTime;
    private float price;
    private String status;
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
