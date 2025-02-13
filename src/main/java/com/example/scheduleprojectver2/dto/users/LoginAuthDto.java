package com.example.scheduleprojectver2.dto.users;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginAuthDto { // 로그인 인증용 DTO

    private Long id;

    private String email;


    @Builder
    public LoginAuthDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
