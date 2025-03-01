package com.szalai.reactive.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    @GetMapping("/time")
    public ResponseEntity<String> getCurrentTime() {
        return runWithDelay(() -> {
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            return ResponseEntity.ok(formattedDateTime);
        });
    }

    private <T> T runWithDelay(Supplier<T> supplier) {
        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return supplier.get();
    }
}
