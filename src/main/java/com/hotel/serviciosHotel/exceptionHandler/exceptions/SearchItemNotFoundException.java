package com.hotel.serviciosHotel.exceptionHandler.exceptions;

public class SearchItemNotFoundException extends Exception{
    public SearchItemNotFoundException(String message) {
        super(message);
    }

    public SearchItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
