package com.luxoft.chatbot.echo.util;

import com.luxoft.chatbot.echo.dto.BotPropertyDTO;
import com.luxoft.chatbot.echo.entity.BotProperty;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public BotPropertyDTO botPropertyEntityToDTO(BotProperty botProperty) {

        String name = botProperty.getBotUserName();
        String token = botProperty.getBotToken();

        return new BotPropertyDTO(name, token);
    }

    public BotProperty botPropertyDTOtoEntity(BotPropertyDTO botPropertyDTO) {

        String name = botPropertyDTO.getBotUserName();
        String token = botPropertyDTO.getBotToken();

        return new BotProperty(name, token);
    }

}
