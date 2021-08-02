package com.luxoft.chatbot.echo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BotPropertyDTO {

    private String botUserName;
    private String botToken;
}
