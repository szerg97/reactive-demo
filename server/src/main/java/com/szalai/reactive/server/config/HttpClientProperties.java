package com.szalai.reactive.server.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class HttpClientProperties {

    @Value("${http-client.base-url}")
    private String baseUrl;
    @Value("${http-client.connection-timeout}")
    private int connectionTimeout;
    @Value("${http-client.response-timeout}")
    private long responseTimeout;
    @Value("${http-client.read-timeout}")
    private long readTimeout;
    @Value("${http-client.write-timeout}")
    private long writeTimeout;
}
