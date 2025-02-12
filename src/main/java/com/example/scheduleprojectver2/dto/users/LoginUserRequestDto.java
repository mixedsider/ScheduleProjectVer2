package com.example.scheduleprojectver2.dto.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginUserRequestDto {
    private final String username;
    private final String password;
}
