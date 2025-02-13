package com.example.scheduleprojectver2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    public NotFoundException(String message) { // 정보를 찾을 수 없는 경우
        super(HttpStatus.NOT_FOUND, message);
    }
}
