package com.example.scheduleprojectver2.repository;

import com.example.scheduleprojectver2.dto.comments.ScheduleInCommentDto;
import com.example.scheduleprojectver2.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Integer countBySchedule_Id(Long scheduleId);

    @Query("SELECT new com.example.scheduleprojectver2.dto.comments.ScheduleInCommentDto(c.user.username, c.contents) " +
            "FROM CommentEntity c " +
            "WHERE c.schedule.id = :scheduleId")
    List<ScheduleInCommentDto> findCommentsByScheduleId(@Param("scheduleId") Long scheduleId);

}
