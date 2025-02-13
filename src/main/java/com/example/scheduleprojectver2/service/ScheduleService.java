package com.example.scheduleprojectver2.service;

import com.example.scheduleprojectver2.dto.comments.ScheduleInCommentDto;
import com.example.scheduleprojectver2.dto.schedules.SchedulePageReponseDto;
import com.example.scheduleprojectver2.dto.schedules.ScheduleResponseDto;
import com.example.scheduleprojectver2.dto.users.UserResponseDto;

import com.example.scheduleprojectver2.entity.ScheduleEntity;
import com.example.scheduleprojectver2.entity.UserEntity;
import com.example.scheduleprojectver2.exception.ErrorDtoException;
import com.example.scheduleprojectver2.exception.ForbiddenException;
import com.example.scheduleprojectver2.repository.CommentRepository;
import com.example.scheduleprojectver2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final CommentRepository commentRepository;

    private final UserService userService;

    public ScheduleResponseDto save(String username, String title, String todo) {
        // todo : 유저 검사하는 로직

        UserEntity findUser = userService.findByUsername(username);


        ScheduleEntity schedule = new ScheduleEntity(title, todo);
        schedule.setUser(findUser);

        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);

        return savedSchedule.toDto();
    }

    public ScheduleResponseDto findById(Long id) {

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        ScheduleResponseDto responseDto = findSchedule.toDto();

        List<ScheduleInCommentDto> comments = commentRepository.findCommentsByScheduleId(findSchedule.getId());

        responseDto.setComments(comments);

        return responseDto;
    }


    public ScheduleEntity findByIdToEntity(Long id) {
        return scheduleRepository.findByIdOrElseThrow(id);
    }

    public Page<SchedulePageReponseDto> findAll(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateDate"));

        return scheduleRepository.findAll(pageRequest)
                .map( item -> SchedulePageReponseDto.builder()
                                .id(item.getId())
                                .username(item.getUser().getUsername())
                                .title(item.getTitle())
                                .todo(item.getTodo())
                                .commentCount(commentRepository.countBySchedule_Id(item.getId()))
                                .build());

    }

    // 일괄 수정
    @Transactional
    public ScheduleResponseDto update(Long id, Long userId, String title, String todo) {

        UserEntity user = userService.findByIdToEntity(userId);

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 수정할 수 있는 권한이 없음
        if( user != findSchedule.getUser() ) {
            throw new ForbiddenException();
        }

        findSchedule.updateTitle(title);
        findSchedule.updateTodo(todo);

        return findSchedule.toDto();
    }

    // 부분 수정
    @Transactional
    public ScheduleResponseDto patch(Long id, Long userId, String title, String todo) {
        if( (title.isEmpty() && todo.isEmpty()) || (!title.isEmpty() && !todo.isEmpty()) ) { // 모두 있거나, 모두 비어있거나
            throw new ErrorDtoException("잘못된 입력입니다.");
        }

        UserEntity user = userService.findByIdToEntity(userId);

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 수정할 수 있는 권한이 없음
        if( user != findSchedule.getUser() ) {
            throw new ForbiddenException();
        }

        if( !title.isEmpty() ) {
            findSchedule.updateTitle(title);
        }
        if( !todo.isEmpty() ) {
            findSchedule.updateTodo(todo);
        }

        return findSchedule.toDto();
    }

    // 일정 조회후 삭제
    @Transactional
    public void delete(Long id, Long userId) {

        UserEntity user = userService.findByIdToEntity(userId);

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 수정할 수 있는 권한이 없음
        if( user != findSchedule.getUser() ) {
            throw new ForbiddenException();
        }

        scheduleRepository.delete(findSchedule);
    }
}
