package com.PingPongManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PingPongManagementExceptionHandler {
    @ExceptionHandler(value = {PingPongManagementRequestException.class})
    public ResponseEntity<Object> handlePingPongManagementException(PingPongManagementRequestException e) {
        // create payload containing exception details
        PingPongManagementException pingPongManagementException = new PingPongManagementException(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST
        );

        // return response entity
        return new ResponseEntity<>(pingPongManagementException, HttpStatus.BAD_REQUEST);
    }
}
