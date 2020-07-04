package com.PingPongManagement.exceptions;

public class PingPongManagementRequestException extends RuntimeException {
    public PingPongManagementRequestException(String message) {
        super(message);
    }

    public PingPongManagementRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
