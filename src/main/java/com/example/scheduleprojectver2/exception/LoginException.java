package com.example.scheduleprojectver2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoginException extends ResponseStatusException {
    public LoginException() {
        super(HttpStatus.UNAUTHORIZED, "로그인 해주세요.");
    }
}
