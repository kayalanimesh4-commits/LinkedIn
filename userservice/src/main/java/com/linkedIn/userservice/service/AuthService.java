package com.linkedIn.userservice.service;

import com.linkedIn.userservice.dto.LoginRequestDto;
import com.linkedIn.userservice.dto.SignUpRequestDto;
import com.linkedIn.userservice.dto.UserDto;
import com.linkedIn.userservice.entity.User;
import com.linkedIn.userservice.exception.BadRequestException;
import com.linkedIn.userservice.repository.UserRepository;
import com.linkedIn.userservice.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
//        Optional<User> byEmail = userRepository.findByEmail(signUpRequestDto.getEmail());
        boolean existsByEmail = userRepository.existsByEmail(signUpRequestDto.getEmail());
        if(existsByEmail) throw new BadRequestException("email already exist");
        User user = new User();
        BeanUtils.copyProperties(signUpRequestDto, user, "password");
        user.setPassword(PasswordUtils.hashPassword(signUpRequestDto.getPassword()));
        User saved = userRepository.save(user);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(saved, dto);
        return dto;
    }



    public String login(LoginRequestDto loginRequestDto) {
       User byEmail = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()-> new RuntimeException("Password is Invalid: " + loginRequestDto.getPassword()));
        boolean checkPassword = PasswordUtils.checkPassword(loginRequestDto.getPassword(), byEmail.getPassword());
        if(!checkPassword) throw new BadRequestException("Incorrect Password");
        return jwtService.generateAccessToken(byEmail);

    }
}
