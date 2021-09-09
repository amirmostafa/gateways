package com.musala.gateways.exceptions;

public class Ipv4NotValidException extends RuntimeException {

    public Ipv4NotValidException() {
        super("Ipv4 is invalid!");
    }
}
