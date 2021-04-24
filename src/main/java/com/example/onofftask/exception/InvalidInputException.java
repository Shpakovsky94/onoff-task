package com.example.onofftask.exception;

public class InvalidInputException extends Exception {

    private final String message;

    public InvalidInputException() {
        this.message = "Invalid data please try another one";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
