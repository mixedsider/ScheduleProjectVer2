package com.example.scheduleprojectver2.dto.schedules;

import com.example.scheduleprojectver2.dto.comments.CommentResponseDto;
import com.example.scheduleprojectver2.dto.comments.ScheduleInCommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ScheduleResponseDto {

    private Long id;

    private String username;

    private String title;

    private String todo;

    @Setter
    private List<ScheduleInCommentDto> comments = new ArrayList<>();

    @Builder
    public ScheduleResponseDto(Long id, String username, String title, String todo) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.todo = todo;
    }
}
