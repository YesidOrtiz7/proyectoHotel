package com.hotel.serviciosHotel.exceptionHandler.exceptions;

public class ItemAlreadyExistException extends Exception{
    public ItemAlreadyExistException() {
        super();
    }

    public ItemAlreadyExistException(String message) {
        super(message);
    }

    public ItemAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
