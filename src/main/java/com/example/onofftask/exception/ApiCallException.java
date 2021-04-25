package com.example.onofftask.exception;

public class ApiCallException extends Exception {

    private final String message;

    public ApiCallException(String url) {
        this.message = "API does not response: " + url;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
