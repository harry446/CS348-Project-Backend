package com.cs348.backendservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingHistoryRequest {
    private int uid;
//    private boolean upcomingOnly;
    private boolean priceAsc;
    private boolean priceDesc;
    // other sorting requirements...
    private boolean likeNumAsc;
    private boolean likeNumDesc;

    public boolean isValid() {
        int trueCount = 0;
//        if (upcomingOnly) trueCount++;
        if (priceAsc) trueCount++;
        if (priceDesc) trueCount++;
        if (likeNumAsc) trueCount++;
        if (likeNumDesc) trueCount++;
        // add more in the future

        return trueCount <= 1;
    }
}
