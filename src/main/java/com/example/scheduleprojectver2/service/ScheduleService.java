package com.example.scheduleprojectver2.service;

import com.example.scheduleprojectver2.dto.schedules.ScheduleResponseDto;
import com.example.scheduleprojectver2.entity.ScheduleEntity;
import com.example.scheduleprojectver2.entity.UserEntity;
import com.example.scheduleprojectver2.exception.ErrorDtoException;
import com.example.scheduleprojectver2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

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

        return findSchedule.toDto();
    }

    public ScheduleEntity findByIdToEntity(Long id) {
        return scheduleRepository.findByIdOrElseThrow(id);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleEntity::toDto)
                .toList();
    }

    // 일괄 수정
    @Transactional
    public ScheduleResponseDto update(Long id, String title, String todo) {

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateTitle(title);
        findSchedule.updateTodo(todo);

        return findSchedule.toDto();
    }

    // 부분 수정
    @Transactional
    public ScheduleResponseDto patch(Long id, String title, String todo) {
        if( (title.isEmpty() && todo.isEmpty()) || (!title.isEmpty() && !todo.isEmpty()) ) { // 모두 있거나, 모두 비어있거나
            throw new ErrorDtoException("잘못된 입력입니다.");
        }

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(id);

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
    public void delete(Long id) {
        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
