package com.cs348.backendservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableSpotRequest {
    private int uid;
    private String location;
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

    // For addition on future attributes
    private boolean freeOnly;
}
