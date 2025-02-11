package com.example.scheduleprojectver2.dto.schedules;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Getter
@RequiredArgsConstructor
public class CreateScheduleRequestDto {

    @NotBlank(message = "이름이 비어있습니다.")
    private final String username;

    @NotNull(message = "제목이 비어있습니다.")
    @Range(min = 1, max = 30, message = "사이즈보다 큽니다.")
    private final String title;

    @NotNull(message = "내용이 비어있습니다.")
    @Range(min = 1, max = 255, message = "사이즈보다 큽니다.")
    private final String todo;
}
