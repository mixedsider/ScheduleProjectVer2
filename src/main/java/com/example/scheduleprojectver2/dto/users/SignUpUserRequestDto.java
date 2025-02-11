package com.example.scheduleprojectver2.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpUserRequestDto {

    @NotBlank
    @Size(min = 1, max = 20)
    private final String username;

    @NotBlank
    @Email
    private final String email;
}
