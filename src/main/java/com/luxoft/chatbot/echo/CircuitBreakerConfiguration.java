package com.luxoft.chatbot.echo;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    private final ReactiveCircuitBreakerFactory cbFactory;

    public CircuitBreakerConfiguration(@Autowired ReactiveCircuitBreakerFactory cbFactory) {
        this.cbFactory = cbFactory;
    }

    @Bean
    public ReactiveCircuitBreaker countCircuitBreaker() {

        cbFactory.configureDefault(s -> new Resilience4JConfigBuilder("Config CB")
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build())
                .circuitBreakerConfig(config())
                .build());

        return cbFactory.create("custom_cb");

    }

    private CircuitBreakerConfig config() {
        return CircuitBreakerConfig
                .custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .slowCallRateThreshold(50.0f)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .build();
    }
}
