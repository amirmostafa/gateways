package com.musala.gateways.exceptions;

public class GatewayFullCapacityException extends RuntimeException {

    public GatewayFullCapacityException() {
        super("No more devices allowed for this gateway");
    }
}
