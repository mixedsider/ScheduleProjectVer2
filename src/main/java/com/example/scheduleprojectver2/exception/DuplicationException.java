package com.example.scheduleprojectver2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DuplicationException extends ResponseStatusException {
    public DuplicationException() {
        super(HttpStatus.BAD_REQUEST, "이미 있는 유저입니다.");
    }
}
