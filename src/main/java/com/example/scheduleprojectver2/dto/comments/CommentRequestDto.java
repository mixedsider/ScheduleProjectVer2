package com.example.scheduleprojectver2.dto.comments;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {

    @NotNull(message = "내용이 비어있습니다.")
    @Size(min = 1, max = 100, message = "내용이 너무 많거나 적습니다.")
    private final String contents;
}
