package com.cs348.backendservice.exceptions;

public class MaximumBookingExceededException extends Exception {
    public MaximumBookingExceededException(String message) {
        super(message);
    }
}
