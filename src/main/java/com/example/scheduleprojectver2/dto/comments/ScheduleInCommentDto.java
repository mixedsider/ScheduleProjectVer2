package com.example.scheduleprojectver2.dto.comments;

import lombok.Getter;

@Getter
public class ScheduleInCommentDto {
    // ScheduleResponse
    // 일정 안에 작성한 댓글 정보

    private String username;

    private String comments;

    public ScheduleInCommentDto(String username, String comments) {
        this.username = username;
        this.comments = comments;
    }
}
