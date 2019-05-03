package com.example.demo.exceptionhandler;

public class OrgException extends RuntimeException {

    public OrgException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrgException(String message) {
        super(message);
    }
}
