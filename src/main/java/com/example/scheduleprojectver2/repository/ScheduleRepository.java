package com.example.scheduleprojectver2.repository;

import com.example.scheduleprojectver2.entity.ScheduleEntity;
import com.example.scheduleprojectver2.exception.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    default ScheduleEntity findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException("없는 데이터입니다.")
        );
    }


    Page<ScheduleEntity> findAll(Pageable pageable);

    void deleteByUser_Id(Long userId);

    List<ScheduleEntity> findByUser_Id(Long userId);
}
