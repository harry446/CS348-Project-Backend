package com.cs348.backendservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakingBookingRequest {
    private String uid;
    private String lid;
    private String sid;
    private String startYear;
    private String startMonth;
    private String startDate;
    private String startHour;
    private String startMinute;
    private String endYear;
    private String endMonth;
    private String endDate;
    private String endHour;
    private String endMinute;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime start_date_and_time;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime end_date_and_time;
}
