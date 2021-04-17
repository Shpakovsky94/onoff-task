package com.example.onofftask.extention;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private final String message;

    public EntityNotFoundException(Long id) {
        this.message = "No Entity with ID : "+id;
    }
    public String getMessage() {
        return message;
    }
}
