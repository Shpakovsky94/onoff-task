package com.example.onofftask.resolver;

import com.example.onofftask.exception.InvalidInputException;

public class StringResolver {

    public static void validateString(String string) throws InvalidInputException {
        if (string == null || string.isEmpty()) {
            throw new InvalidInputException();
        }
    }
}
