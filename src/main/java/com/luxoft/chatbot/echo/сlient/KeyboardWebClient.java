package com.luxoft.chatbot.echo.сlient;

import com.luxoft.chatbot.echo.dto.KeyboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KeyboardWebClient {

    private final WebClient webClient;
    private final String uri;

    public KeyboardWebClient(@Autowired WebClient.Builder builder,
                             @Value("${service.keyboard.url}") String keyBoardServiceUrl,
                             @Value("${service.keyboard.query.get.by-name}") String uri) {
            this.webClient = builder.baseUrl(keyBoardServiceUrl).build();
            this.uri = uri;
        }

        public Mono<KeyboardDTO> getKeyboard () {

            return webClient
                    .get()
                    .uri(uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(KeyboardDTO.class);
        }
    }
