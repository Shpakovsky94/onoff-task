package com.example.onofftask.validator;

import com.example.onofftask.exception.InvalidInputException;

public class DataValidator {

    public static void validateString(String string) throws InvalidInputException {
        if (string == null || string.isEmpty()) {
            throw new InvalidInputException();
        }

    }
}
