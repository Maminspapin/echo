package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dao.BotPropertyRepository;
import com.luxoft.chatbot.echo.entity.BotProperties;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertiesFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BotService {

    private final BotPropertyRepository botPropertyRepository;

    @Autowired
    private BotService(BotPropertyRepository botPropertyRepository) {
        this.botPropertyRepository = botPropertyRepository;
    }

    public BotProperties getBotProperties() throws NoSuchBotPropertiesFound {
        Optional<BotProperties> result = botPropertyRepository.findById(1);
        if (result.isEmpty()) {
            throw new NoSuchBotPropertiesFound("Properties for bot with id=1 is not found in DB. Check it!");
        }

        return result.get();
    }
}
