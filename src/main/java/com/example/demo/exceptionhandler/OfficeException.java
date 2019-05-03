package com.example.demo.exceptionhandler;

public class OfficeException extends RuntimeException {

    public OfficeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfficeException(String message) {
        super(message);
    }
}