package com.example.scheduleprojectver2.entity;

import com.example.scheduleprojectver2.dto.schedules.CreateScheduleRequestDto;
import com.example.scheduleprojectver2.dto.schedules.ScheduleResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public ScheduleEntity(String title, String todo) {
        this.title = title;
        this.todo = todo;
    }

    public static ScheduleEntity toEntity(CreateScheduleRequestDto dto) {
        return new ScheduleEntity(dto.getTitle(), dto.getTodo());
    }

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
