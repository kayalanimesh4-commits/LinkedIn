package com.linkedIn.userservice.service;

import com.linkedIn.userservice.dto.SignUpRequestDto;
import com.linkedIn.userservice.dto.UserDto;
import com.linkedIn.userservice.exception.BadRequestException;
import com.linkedIn.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private UserRepository userRepository;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        boolean byEmail = userRepository.findByEmail(signUpRequestDto.getEmail());
        if(byEmail) throw new BadRequestException("email already exist");

        return null;
    }
}
