package com.musala.gateways.exceptions;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(long uid) {
        super(String.format("Device with uid %d not found!", uid));
    }
}
