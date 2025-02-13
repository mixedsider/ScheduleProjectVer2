package com.example.scheduleprojectver2.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchUserRequestDto {
    // 부분 수정용 Dto

    private String username;

    private String email;
}
