package com.example.scheduleprojectver2.controller;

import com.example.scheduleprojectver2.dto.schedules.*;
import com.example.scheduleprojectver2.dto.users.LoginAuthDto;
import com.example.scheduleprojectver2.filter.Const;
import com.example.scheduleprojectver2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 저장
    @PostMapping("")
    public ResponseEntity<ScheduleResponseDto> save(
            @Validated @RequestBody CreateScheduleRequestDto requestDto,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
            ) {

        ScheduleResponseDto responseDto = scheduleService.save(
                userDto.getId(),
                requestDto.getTitle(),
                requestDto.getTodo()
        );

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> find(@PathVariable Long id) {

        ScheduleResponseDto responseDto = scheduleService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 일정 다건 조회
    @GetMapping("")
    public ResponseEntity<Page<SchedulePageReponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<SchedulePageReponseDto> responseDtos = scheduleService.findAll(page, size);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    // 일정 일괄 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(
            @PathVariable Long id,
            @Validated @RequestBody UpdateScheduleRequestDto requestDto,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {

        ScheduleResponseDto responseDto = scheduleService.update(id, userDto.getId() ,requestDto.getTitle(), requestDto.getTodo());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 일정 부분 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> patch(
            @PathVariable Long id,
            @RequestBody PatchScheduleRequestDto requestDto,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {
        ScheduleResponseDto responseDto = scheduleService.patch(id, userDto.getId(), requestDto.getTitle(), requestDto.getTodo());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {
        scheduleService.delete(id, userDto.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
