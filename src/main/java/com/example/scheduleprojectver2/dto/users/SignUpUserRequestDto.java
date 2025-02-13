package com.example.scheduleprojectver2.dto.users;


import com.example.scheduleprojectver2.filter.Const;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@RequiredArgsConstructor
public class SignUpUserRequestDto {

    @NotBlank
    @Size(min = 1, max = 4)
    private final String username;

    @NotBlank
    @Size(min = 1, max = 15)
    private final String password;

    @NotBlank
    @Pattern(regexp = Const.EMAIL_REGEX, message = "이메일 형식이 아닙니다.")
    private final String email;
}
