package com.example.scheduleprojectver2.controller;

import com.example.scheduleprojectver2.dto.users.*;
import com.example.scheduleprojectver2.filter.Const;
import com.example.scheduleprojectver2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Validated @RequestBody SignUpUserRequestDto requestDto) {
        UserResponseDto responseDto = userService.save(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginAuthDto> login(
            @Validated @RequestBody LoginUserRequestDto requestDto,
            HttpServletRequest httpServletRequest
    ) {
        // 회원 조회
        LoginAuthDto responseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());

        // 세선 생성 후 전달
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(Const.LOGIN_USER, responseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest httpServletRequest
    ) {
        HttpSession session = httpServletRequest.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }

        return ResponseEntity.ok("success");
    }

    // 회원 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto responseDto = userService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 회원 목록 조회
    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userList = userService.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // 회원 정보 수정 ( 전체 )
    @PutMapping("")
    public ResponseEntity<UserResponseDto> update(
            @Validated @RequestBody UpdateUserRequestDto requestDto,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {
        UserResponseDto responseDto = userService.update(userDto.getId(), requestDto.getUsername(), requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 회원 정보 수정 ( 일부 )
    @PatchMapping("")
    public ResponseEntity<UserResponseDto> patch(
            @RequestBody PatchUserRequestDto requestDto,
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {
        UserResponseDto responseDto = userService.patch(userDto.getId(), requestDto.getUsername(), requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("")
    public ResponseEntity<Void> delete(
            @SessionAttribute(Const.LOGIN_USER) LoginAuthDto userDto
    ) {
        userService.delete(userDto.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
