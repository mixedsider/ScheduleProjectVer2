package com.example.scheduleprojectver2.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(ErrorDtoException.class)
    public ErrorResult errorDtoExHandle(ErrorDtoException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResult notFoundExHandle(NotFoundException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult MethodArgumentNotValidExHandle(MethodArgumentNotValidException e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    public ErrorResult LoginExHandle(LoginException e) {
        return new ErrorResult("아이디 혹은 비밀번호가 잘못되었습니다." + e);

    }
}
