package com.cs348.backendservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingHistoryRequest {
    private String uid;
    private boolean priceAsc;
    private boolean priceDesc;
}
