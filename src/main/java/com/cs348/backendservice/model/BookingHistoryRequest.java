package com.cs348.backendservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingHistoryRequest {
    private int uid;
    private boolean upcomingOnly;
    private boolean priceAsc;
    private boolean priceDesc;
    // other sorting requirements...

    public boolean isValid() {
        int trueCount = 0;
        if (upcomingOnly) trueCount++;
        if (priceAsc) trueCount++;
        if (priceDesc) trueCount++;
        // add more in the future

        return trueCount <= 1;
    }
}
