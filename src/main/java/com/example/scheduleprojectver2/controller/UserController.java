package com.example.scheduleprojectver2.controller;

import com.example.scheduleprojectver2.dto.users.*;
import com.example.scheduleprojectver2.exception.LoginException;
import com.example.scheduleprojectver2.filter.Const;
import com.example.scheduleprojectver2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Validated @RequestBody SignUpUserRequestDto requestDto) {
        UserResponseDto responseDto = userService.save(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @Validated @RequestBody LoginUserRequestDto requestDto,
            HttpServletRequest httpServletRequest
    ) {
        UserResponseDto responseDto = userService.login(requestDto.getUsername(), requestDto.getPassword());

        HttpSession session = httpServletRequest.getSession();

        session.setAttribute(Const.LOGIN_USER, responseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto responseDto = userService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userList = userService.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @Validated @RequestBody UpdateUserRequestDto requestDto
    ) {
        UserResponseDto responseDto = userService.update(id, requestDto.getUsername(), requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patch(
            @PathVariable Long id,
            @RequestBody PatchUserRequestDto requestDto
    ) {
        UserResponseDto responseDto = userService.patch(id, requestDto.getUsername(), requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
