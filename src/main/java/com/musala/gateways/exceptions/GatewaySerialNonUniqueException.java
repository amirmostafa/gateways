package com.musala.gateways.exceptions;

public class GatewaySerialNonUniqueException extends RuntimeException {

    public GatewaySerialNonUniqueException(String serialNumber) {
        super(String.format("Gateway with serial number %s is already existing!", serialNumber));
    }
}
