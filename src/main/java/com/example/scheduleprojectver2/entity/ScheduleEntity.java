package com.example.scheduleprojectver2.entity;

import com.example.scheduleprojectver2.dto.schedules.CreateScheduleRequestDto;
import com.example.scheduleprojectver2.dto.schedules.ScheduleResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "schedule")
public class ScheduleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String todo;

    @Setter
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CommentEntity> comments;

    public ScheduleEntity(UserEntity user, String title, String todo) {
        this.user = user;
        this.title = title;
        this.todo = todo;
    }

    // DTO 로 변환
    public ScheduleResponseDto toDto() {
        return new ScheduleResponseDto(this.id, this.user.getUsername(), this.title, this.todo);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateTodo(String todo) {
        this.todo = todo;
    }
}
