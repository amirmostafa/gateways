package com.musala.gateways.exceptions;

public class DeviceUidNonUniqueException extends RuntimeException {

    public DeviceUidNonUniqueException(long uid) {
        super(String.format("Device with uid %d is already existing!", uid));
    }
}
