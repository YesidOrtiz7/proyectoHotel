package com.hotel.serviciosHotel.exceptionHandler.exceptions;

public class InvalidCharacterException extends Exception{
    public InvalidCharacterException() {
        super();
    }

    public InvalidCharacterException(String message) {
        super(message);
    }

    public InvalidCharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCharacterException(Throwable cause) {
        super(cause);
    }
}
