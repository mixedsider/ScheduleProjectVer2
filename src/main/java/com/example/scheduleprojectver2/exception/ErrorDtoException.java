package com.example.scheduleprojectver2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErrorDtoException extends ResponseStatusException {
    public ErrorDtoException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
