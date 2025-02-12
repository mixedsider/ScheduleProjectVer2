package com.example.scheduleprojectver2.repository;

import com.example.scheduleprojectver2.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
