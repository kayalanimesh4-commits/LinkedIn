package com.linkedIn.connection_service.controller;

import com.linkedIn.connection_service.entity.Person;
import com.linkedIn.connection_service.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionsService;

    @GetMapping("/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections());
    }
}
