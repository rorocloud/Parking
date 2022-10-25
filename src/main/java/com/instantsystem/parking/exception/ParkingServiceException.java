package com.instantsystem.parking.exception;

public abstract class ParkingServiceException extends Exception {
    public ParkingServiceException() {}
    public ParkingServiceException(String message) { super(message); }
    public ParkingServiceException(String message, Throwable cause) { super(message, cause); }
    public ParkingServiceException(Throwable cause) { super(cause); }
}
