package com.cs348.backendservice.tools;

import org.springframework.stereotype.Component;

@Component
public class Tools {
    public String convertToTime(String startYear, String startMonth, String startDate, String startHour, String startMinute) {
        return startYear + "-" + startMonth + "-" + startDate + " " + startHour + ":" + startMinute + ":00";
    }
}
