package com.luxoft.chatbot.echo.сlient;

import com.luxoft.chatbot.echo.dto.KeyboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KeyboardWebClient {

    private final WebClient webClient;

    public KeyboardWebClient(@Autowired WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8082").build();
    }

    public Mono<KeyboardDTO> getKeyboard() {

        return webClient
                .get()
                .uri("/api/keyboard/?name=test")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(KeyboardDTO.class);
    }
}
