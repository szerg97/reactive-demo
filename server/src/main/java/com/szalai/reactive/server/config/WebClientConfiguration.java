package com.szalai.reactive.server.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    private final HttpClientProperties http;
    private final ConnectionProviderProperties conn;

    @Bean
    public WebClient webClient() {
        ConnectionProvider connectionProvider = createConnectionProvider();
        HttpClient httpClient = createHttpClient(connectionProvider);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    private HttpClient createHttpClient(ConnectionProvider connectionProvider) {
        return HttpClient.create(connectionProvider)
                .baseUrl(http.getBaseUrl())
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, http.getConnectionTimeout())
                .responseTimeout(Duration.ofMillis(http.getResponseTimeout()))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(http.getReadTimeout(), TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(http.getWriteTimeout(), TimeUnit.MILLISECONDS)));

    }

    private ConnectionProvider createConnectionProvider() {
        return ConnectionProvider.builder("fixed")
                .maxConnections(conn.getMaxConnections())
                .pendingAcquireMaxCount(conn.getPendingAcquireMaxCount())
                .build();
    }
}
