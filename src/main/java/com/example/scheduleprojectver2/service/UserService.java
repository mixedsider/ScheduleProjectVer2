package com.example.scheduleprojectver2.service;


import com.example.scheduleprojectver2.config.PasswordEncoder;

import com.example.scheduleprojectver2.dto.users.LoginAuthDto;
import com.example.scheduleprojectver2.dto.users.UserResponseDto;
import com.example.scheduleprojectver2.entity.UserEntity;
import com.example.scheduleprojectver2.exception.DuplicationException;
import com.example.scheduleprojectver2.exception.ErrorDtoException;
import com.example.scheduleprojectver2.exception.LoginException;

import com.example.scheduleprojectver2.exception.NotFoundException;
import com.example.scheduleprojectver2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;


    // 회원가입
    public UserResponseDto save(String username, String password, String email) {

        if( userRepository.existsByEmail(email) ) {
            throw new DuplicationException();
        }

        String encodePassword = passwordEncoder.encode(password);

        UserEntity user = new UserEntity(username, encodePassword, email);

        UserEntity savedUser = userRepository.save(user);

        return savedUser.toDto();
    }

    // 로그인
    public LoginAuthDto login(String email, String password) {


        UserEntity user = findByEmail(email);

        // 암호화 비밀번호와 비교
        if( !passwordEncoder.matches(password, user.getPassword()) ) {
            throw new LoginException();
        }

        return new LoginAuthDto(user.getId(), email); // 로그인용 DTO

    }

    // 회원조회
    public UserResponseDto findById(Long id) {
        UserEntity findUser = userRepository.findByIdOrElseThrow(id);

        return findUser.toDto();
    }


    // 회원조회 리턴값 Entity
    public UserEntity findByIdToEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("없는 유저입니다.")
                );
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 이름으로 회원조회
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        user.orElseThrow(
                () -> new NotFoundException("아이디 혹은 비밀번호를 잘못 입력 하셨습니다.")
        );
        return user.get();

    }

    // 모든 회원 조회
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toDto)
                .toList();
    }

    // 회원 정보 수정
    @Transactional
    public UserResponseDto update(Long id, String username, String email) {
        UserEntity findUser = userRepository.findByIdOrElseThrow(id);

        findUser.updateUsername(username);
        findUser.updateEmail(email);

        return findUser.toDto();
    }

    // 회원 정보 부분 수정
    @Transactional
    public UserResponseDto patch(Long id, String username, String email) {
        if ((username.isEmpty() && email.isEmpty()) || (!username.isEmpty() && !email.isEmpty())) { // 모두 있거나, 모두 없거나
            throw new ErrorDtoException("잘못된 입력입니다.");
        }

        UserEntity findUser = userRepository.findByIdOrElseThrow(id);

        if( !username.isEmpty() ) {
            findUser.updateUsername(username);
        }

        if( !email.isEmpty() ) {
            findUser.updateEmail(email);
        }

        return findUser.toDto();
    }


    // 회원 탈퇴
    @Transactional
    public void delete(Long id) {
        UserEntity deleteUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(deleteUser);
    }
}
