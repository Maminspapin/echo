package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dao.BotRepository;
import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertyFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BotService {

    private final BotRepository botRepository;

    @Autowired
    private BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public BotProperty getBotProperties() throws NoSuchBotPropertyFound {
        Optional<BotProperty> result = botRepository.findById(1);
        if (result.isEmpty()) {
            throw new NoSuchBotPropertyFound("Properties for bot with id=1 is not found in DB. Check it!");
        }

        return result.get();
    }
}
