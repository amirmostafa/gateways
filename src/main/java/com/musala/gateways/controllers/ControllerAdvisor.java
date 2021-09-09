package com.musala.gateways.controllers;

import com.musala.gateways.dto.ApiExceptionModel;
import com.musala.gateways.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    // add here exceptions with httpStatus.notFound
    @ExceptionHandler({GatewayNotFoundException.class, DeviceNotFoundException.class})
    public ResponseEntity<ApiExceptionModel> HandleNotFoundStatusExceptions(final RuntimeException ex, final WebRequest request) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ApiExceptionModel(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // add here exceptions with httpStatus.badRequest
    @ExceptionHandler({GatewaySerialNonUniqueException.class, DeviceUidNonUniqueException.class, Ipv4NotValidException.class, GatewayFullCapacityException.class, InvalidDeviceStatusException.class})
    public ResponseEntity<ApiExceptionModel> handleBadRequestExceptions(final RuntimeException ex, final WebRequest request) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ApiExceptionModel(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiExceptionModel> handleOtherExceptions(final RuntimeException ex, final WebRequest request) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ApiExceptionModel(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
