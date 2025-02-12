package com.example.scheduleprojectver2.controller;

import com.example.scheduleprojectver2.dto.comments.CommentRequestDto;
import com.example.scheduleprojectver2.dto.comments.CommentResponseDto;
import com.example.scheduleprojectver2.dto.users.UserResponseDto;
import com.example.scheduleprojectver2.filter.Const;
import com.example.scheduleprojectver2.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @PostMapping("")
    public ResponseEntity<CommentResponseDto> save(
            @RequestParam Long scheduleId,
            @Validated @RequestBody CommentRequestDto requestDto,
            HttpServletRequest httpservletRequest
    ) {
        HttpSession session = httpservletRequest.getSession(false);

        UserResponseDto user = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        CommentResponseDto responseDto = commentService.save(scheduleId, user.getId(), requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findById(
            @PathVariable Long commentId
    ) {
        return new ResponseEntity<>(commentService.findById(commentId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateContents(
            @RequestParam Long commentId,
            @Validated @RequestBody CommentRequestDto requestDto
    ) {

        CommentResponseDto responseDto = commentService.update(commentId, requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long commentId
    ) {
        commentService.delete(commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
