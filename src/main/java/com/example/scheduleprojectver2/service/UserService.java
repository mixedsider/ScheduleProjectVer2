package com.example.scheduleprojectver2.service;

import com.example.scheduleprojectver2.config.PasswordEncoder;
import com.example.scheduleprojectver2.dto.users.UserResponseDto;
import com.example.scheduleprojectver2.entity.UserEntity;
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

    private final PasswordEncoder passwordEncoder;

    public UserResponseDto save(String username, String password, String email) {

        String encodePassword = passwordEncoder.encode(password);

        UserEntity user = new UserEntity(username, encodePassword, email);

        UserEntity savedUser = userRepository.save(user);

        return savedUser.toDto();
    }

    public UserResponseDto login(String username, String password) {

        UserEntity user1 = findByUsername(username);


        if( !passwordEncoder.matches(password, user1.getPassword()) ) {
            throw new LoginException();
        }

        return user1.toDto();
    }

    public UserResponseDto findById(Long id) {
        UserEntity findUser = userRepository.findByIdOrElseThrow(id);

        return findUser.toDto();
    }

    public UserEntity findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        user.orElseThrow(
                () -> new NotFoundException("아이디 혹은 비밀번호를 잘못 입력 하셨습니다.")
        );
        return user.get();
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toDto)
                .toList();
    }

    @Transactional
    public UserResponseDto update(Long id, String username, String email) {
        UserEntity findUser = userRepository.findByIdOrElseThrow(id);

        findUser.updateUsername(username);
        findUser.updateEmail(email);

        return findUser.toDto();
    }

    @Transactional
    public UserResponseDto patch(Long id, String username, String email) {
        if ((username.isEmpty() && email.isEmpty()) || (!username.isEmpty() && !email.isEmpty())) {
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

    public void delete(Long id) {
        UserEntity deleteUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(deleteUser);
    }
}
