package com.example.scheduleprojectver2.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserResponseDto {

    private Long id;

    private String username;


    @Builder
    public UserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
