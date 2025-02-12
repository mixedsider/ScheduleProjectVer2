package com.example.scheduleprojectver2.repository;

import com.example.scheduleprojectver2.entity.UserEntity;
import com.example.scheduleprojectver2.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    default UserEntity findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException("없는 유저입니다.")
        );
    }

    UserEntity findByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
