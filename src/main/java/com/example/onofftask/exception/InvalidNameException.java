package com.example.onofftask.exception;

public class InvalidNameException extends Exception {

    private final String message;

    public InvalidNameException() {
        this.message = "Name should be from the list: https://api.bitfinex.com/v1/symbols";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
