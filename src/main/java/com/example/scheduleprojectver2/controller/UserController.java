package com.example.scheduleprojectver2.controller;

import com.example.scheduleprojectver2.dto.users.PatchUserRequestDto;
import com.example.scheduleprojectver2.dto.users.SignUpUserRequestDto;
import com.example.scheduleprojectver2.dto.users.UpdateUserRequestDto;
import com.example.scheduleprojectver2.dto.users.UserResponseDto;
import com.example.scheduleprojectver2.service.UserService;
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
        UserResponseDto responseDto = userService.save(requestDto.getUsername(), requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
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
