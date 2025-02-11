package com.example.scheduleprojectver2.dto.schedules;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private Long id;

    private String username;

    private String title;

    private String todo;

    @Builder
    public ScheduleResponseDto(Long id, String username, String title, String todo) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.todo = todo;
    }
}
