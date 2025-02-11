package com.example.scheduleprojectver2.dto.users;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserRequestDto {

    @NotNull
    @Size(min = 1, max = 20)
    private final String username;

    @NotNull
    @Size(min = 1, max = 20)
    private final String email;
}
