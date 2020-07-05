package com.PingPongManagement.exceptions;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Exception exception) {
        super(message, exception);
    }
}
