package com.example.onofftask.exception;

public class InvalidInputException extends Exception {

    private final String message;

    public InvalidInputException() {
        this.message = "Invalid request data, please provide 'name', 'amount', and 'wallet' values";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
