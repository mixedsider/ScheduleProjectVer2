package com.example.scheduleprojectver2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErrorDtoException extends ResponseStatusException {
    public ErrorDtoException(String message) { // DTO 가 잘못 입력된 경우
        super(HttpStatus.BAD_REQUEST, message);
    }
}
