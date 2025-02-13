package com.example.scheduleprojectver2.dto.comments;

import lombok.Getter;

@Getter
public class ScheduleInCommentDto {

    private String username;

    private String comments;

    public ScheduleInCommentDto(String username, String comments) {
        this.username = username;
        this.comments = comments;
    }
}
