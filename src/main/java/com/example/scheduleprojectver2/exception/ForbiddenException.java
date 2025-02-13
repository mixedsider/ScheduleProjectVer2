package com.example.scheduleprojectver2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenException extends ResponseStatusException {
    public ForbiddenException() { // 403 : 수정 할 데이터가 유저의 데이터가 아닌 경우
        super(HttpStatus.FORBIDDEN, "권한이 없습니다.");
    }
}
