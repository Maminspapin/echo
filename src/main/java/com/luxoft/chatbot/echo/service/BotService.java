package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dao.BotPropertyRepository;
import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertyFound;
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

    public BotProperty getBotProperties() throws NoSuchBotPropertyFound {
        Optional<BotProperty> result = botPropertyRepository.findById(1);
        if (result.isEmpty()) {
            throw new NoSuchBotPropertyFound("Properties for bot with id=1 is not found in DB. Check it!");
        }

        return result.get();
    }
}
