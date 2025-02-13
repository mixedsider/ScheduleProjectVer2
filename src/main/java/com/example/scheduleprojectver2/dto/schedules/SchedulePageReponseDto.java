package com.example.scheduleprojectver2.dto.schedules;

import lombok.Builder;
import lombok.Getter;


@Getter
public class SchedulePageReponseDto {

    private Long id;

    private String username;

    private String title;

    private String todo;

    private Integer commentCount;

    @Builder
    public SchedulePageReponseDto(Long id, String username, String title, String todo, Integer commentCount) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.todo = todo;
        this.commentCount = commentCount;
    }
}
