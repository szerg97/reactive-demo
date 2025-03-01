package com.szalai.reactive.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final WebClient webClient;

    public String getCurrentTimeFromClient() {
        Mono<String> stringMono = webClient.get().uri("/client/time").retrieve().bodyToMono(String.class);
        return stringMono.block();
    }
}
