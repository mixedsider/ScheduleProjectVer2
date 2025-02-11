package com.example.scheduleprojectver2.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    @Builder
    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
