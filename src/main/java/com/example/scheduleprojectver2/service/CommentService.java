package com.example.scheduleprojectver2.service;

import com.example.scheduleprojectver2.dto.comments.CommentRequestDto;
import com.example.scheduleprojectver2.dto.comments.CommentResponseDto;
import com.example.scheduleprojectver2.entity.CommentEntity;
import com.example.scheduleprojectver2.entity.ScheduleEntity;
import com.example.scheduleprojectver2.entity.UserEntity;
import com.example.scheduleprojectver2.exception.ForbiddenException;
import com.example.scheduleprojectver2.exception.NotFoundException;
import com.example.scheduleprojectver2.repository.CommentRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final ScheduleService scheduleService;

    public CommentResponseDto save(Long scheduleId, Long userId, String comments) {

        CommentEntity comment = new CommentEntity(comments);

        ScheduleEntity schedule = scheduleService.findByIdToEntity(scheduleId);

        UserEntity user = userService.findByIdToEntity(userId);

        comment.setSchedule(schedule);
        comment.setUser(user);

        CommentEntity savedComment = commentRepository.save(comment);

        return savedComment.toDto();
    }

    public CommentResponseDto findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("해당 댓글은 없습니다.")
                ).toDto();
    }

    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(CommentEntity::toDto)
                .toList();
    }

    @Transactional
    public CommentResponseDto update(Long commentId, Long userId, String contents) {

        UserEntity user = userService.findByIdToEntity(userId);

        Optional<CommentEntity> findComment = commentRepository.findById(commentId);

        findComment.orElseThrow(
                () -> new NotFoundException("해당 댓글은 없습니다.")
        );

        // 권한이 없는 경우
        if( user != findComment.get().getUser() ) {
            throw new ForbiddenException();
        }

        CommentEntity comment = findComment.get();

        comment.updateContents(contents);

        return comment.toDto();
    }

    public void delete(Long commentId, Long userId) {

        UserEntity user = userService.findByIdToEntity(userId);

        CommentEntity findComment = commentRepository.findById(commentId)
                .orElseThrow(
                        () -> new NotFoundException("없는 댓글입니다.")
                );

        // 권한이 없는 경우
        if( user != findComment.getUser() ) {
            throw new ForbiddenException();
        }

        commentRepository.deleteById(commentId);
    }
}
