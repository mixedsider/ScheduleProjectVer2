package com.example.scheduleprojectver2.entity;

import com.example.scheduleprojectver2.dto.comments.CommentResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    @Column(nullable = false)
    private String contents;

    public CommentEntity(String contents) {
        this.contents = contents;
    }

    public CommentResponseDto toDto() {
        return CommentResponseDto.builder()
                .id(this.id)
                .username(this.user.getUsername())
                .schedule(this.schedule.getId())
                .contents(this.contents)
                .build();
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
