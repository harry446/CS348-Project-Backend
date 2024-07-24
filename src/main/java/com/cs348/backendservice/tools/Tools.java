package com.cs348.backendservice.tools;

import org.springframework.stereotype.Component;

@Component
public class Tools {
    public String convertToTime(String startYear, String startMonth, String startDate, String startHour, String startMinute) {
        return startYear + "-" + startMonth + "-" + startDate + " " + startHour + ":" + startMinute + ":00";
    }

    public float convertDuration(String startYear, String startMonth, String startDate, String startHour, String startMin,
                                 String endYear, String endMonth, String endDate, String endHour, String endMin) {
        long syear = Long.parseLong(startYear);
        long smonth = Long.parseLong(startMonth);
        long sdate = Long.parseLong(startDate);
        long shour = Long.parseLong(startHour);
        long smin = Long.parseLong(startMin);
        long eyear = Long.parseLong(endYear);
        long emonth = Long.parseLong(endMonth);
        long edate = Long.parseLong(endDate);
        long ehour = Long.parseLong(endHour);
        long emin = Long.parseLong(endMin);

        return (float)(((eyear*525600 + emonth*43200 + edate*1440 + ehour*60 + emin) - (syear*525600 + smonth*43200 + sdate*1440 + shour*60 + smin))/60.0);
    }
}
