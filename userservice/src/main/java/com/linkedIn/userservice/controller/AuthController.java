package com.linkedIn.userservice.controller;

import com.linkedIn.userservice.dto.LoginRequestDto;
import com.linkedIn.userservice.dto.SignUpRequestDto;
import com.linkedIn.userservice.dto.UserDto;
import com.linkedIn.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
      UserDto userDto = authService.signUp(signUpRequestDto);
      return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        String login = authService.login(loginRequestDto);
        return ResponseEntity.ok(login);
    }
}
