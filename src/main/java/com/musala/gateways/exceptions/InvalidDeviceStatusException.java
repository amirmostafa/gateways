package com.musala.gateways.exceptions;

public class InvalidDeviceStatusException extends RuntimeException {

    public InvalidDeviceStatusException() {
        super("Device Status is invalid!");
    }
}
