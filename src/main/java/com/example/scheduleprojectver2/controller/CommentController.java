package com.example.scheduleprojectver2.controller;

import com.example.scheduleprojectver2.dto.comments.CommentRequestDto;
import com.example.scheduleprojectver2.dto.comments.CommentResponseDto;
import com.example.scheduleprojectver2.dto.users.LoginAuthDto;
import com.example.scheduleprojectver2.filter.Const;
import com.example.scheduleprojectver2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장
    @PostMapping("")
    public ResponseEntity<CommentResponseDto> save(
            @RequestParam Long scheduleId,
            @Validated @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {
        CommentResponseDto responseDto = commentService.save(scheduleId, userDto.getId(), requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 댓글 단건 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findById(
            @PathVariable Long commentId
    ) {
        return new ResponseEntity<>(commentService.findById(commentId), HttpStatus.OK);
    }

    // 댓글 다건 조회
    @GetMapping("")
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateContents(
            @PathVariable Long commentId,
            @Validated @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {

        CommentResponseDto responseDto = commentService.update(commentId, userDto.getId(), requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long commentId,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {
        commentService.delete(commentId, userDto.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
