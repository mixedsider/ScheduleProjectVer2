package com.example.scheduleprojectver2.dto.comments;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CommentResponseDto {

    private Long id;

    private String username;

    private Long schedule;

    private String contents;

    @Builder
    public CommentResponseDto(Long id, Long schedule, String username, String contents) {
        this.id = id;
        this.schedule = schedule;
        this.username = username;
        this.contents = contents;
    }
}
