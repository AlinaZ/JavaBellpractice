package com.example.demo.controller;

public class ErrorResponse {

    private String message;

    public ErrorResponse(String error) {
        this.message = error;
    }

    public String getError() {
        return message;
    }

    public void setError(String error) {
        this.message = error;
    }
}
