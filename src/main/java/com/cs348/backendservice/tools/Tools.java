package com.cs348.backendservice.tools;

import org.springframework.stereotype.Component;

@Component
public class Tools {
    public String convertToTime(String startYear, String startMonth, String startDate, String startHour, String startMinute) {
        return startYear + "-" + startMonth + "-" + startDate + " " + startHour + ":" + startMinute + ":00";
    }

    public float convertDuration(String startYear, String startMonth, String startDate, String startHour, String startMin,
                                 String endYear, String endMonth, String endDate, String endHour, String endMin) {
        float syear = Float.parseFloat(startYear);
        float smonth = Float.parseFloat(startMonth);
        float sdate = Float.parseFloat(startDate);
        float shour = Float.parseFloat(startHour);
        float smin = Float.parseFloat(startMin);
        float eyear = Float.parseFloat(endYear);
        float emonth = Float.parseFloat(endMonth);
        float edate = Float.parseFloat(endDate);
        float ehour = Float.parseFloat(endHour);
        float emin = Float.parseFloat(endMin);

        return (eyear*525600 + emonth*43200 + edate*1440 + ehour*60 + emin) - (syear*525600 + smonth*43200 + sdate*1440 + shour*60 + smin);
    }
}
