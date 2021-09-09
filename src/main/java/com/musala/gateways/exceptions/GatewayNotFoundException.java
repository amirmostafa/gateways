package com.musala.gateways.exceptions;

public class GatewayNotFoundException extends RuntimeException {

    public GatewayNotFoundException(String serialNumber) {
        super(String.format("Gateway with serial number %s not found!", serialNumber));
    }
}
