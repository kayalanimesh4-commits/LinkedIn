package com.linkedIn.connection_service.service;

import com.linkedIn.connection_service.auth.UserContextHolder;
import com.linkedIn.connection_service.entity.Person;
import com.linkedIn.connection_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections() {
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting first degree connections for user with id: {}", userId);

        return personRepository.findAllByUserId(userId.toString());
    }
}
