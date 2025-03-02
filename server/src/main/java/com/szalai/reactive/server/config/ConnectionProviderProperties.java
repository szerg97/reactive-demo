package com.szalai.reactive.server.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ConnectionProviderProperties {

    @Value("${connection-provider.max-connections}")
    private int maxConnections;
    @Value("${connection-provider.pending-acquire-max-count}")
    private int pendingAcquireMaxCount;
}
