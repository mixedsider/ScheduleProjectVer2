package com.example.scheduleprojectver2.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchUserRequestDto {

    private String username;

    private String email;
}
