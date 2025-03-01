package com.szalai.reactive.server.controller;

import com.szalai.reactive.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService service;

    @GetMapping("/current-time")
    public ResponseEntity<String> getCurrentTime() {
        return ResponseEntity.ok(service.getCurrentTimeFromClient());
    }
}
