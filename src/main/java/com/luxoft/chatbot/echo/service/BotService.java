package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dao.BotPropertyRepository;
import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertyFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BotService {

    private final BotPropertyRepository botPropertyRepository;

    @Autowired
    private BotService(BotPropertyRepository botPropertyRepository) {
        this.botPropertyRepository = botPropertyRepository;
    }

    public List<BotProperty> getAllBotProperties() {
        List<BotProperty> result = new ArrayList<>();

        for (BotProperty botProperty : botPropertyRepository.findAll()) {
            result.add(botProperty);
        }

        return result;
    }

    public void saveBotProperty(BotProperty botProperty) {
        botPropertyRepository.save(botProperty);
    }

    public BotProperty getBotPropertyById(int id) throws NoSuchBotPropertyFound {
        Optional<BotProperty> result = botPropertyRepository.findById(id);
        if (result.isEmpty()) {
            throw new NoSuchBotPropertyFound("Properties for bot with id=" + id + " is not found in DB. Check it!");
        }

        return result.get();
    }

    public BotProperty getBotPropertyByName(String name) throws NoSuchBotPropertyFound {
        Optional<BotProperty> result = botPropertyRepository.findBotPropertyByBotUserName(name);
        if (result.isEmpty()) {
            throw new NoSuchBotPropertyFound("Properties for bot with name=" + name + " is not found in DB. Check it!");
        }

        return result.get();
    }

    public void deleteBotPropertyByName(String name) {
        botPropertyRepository.deleteBotPropertyByBotUserName(name);
    }

    public void deleteBotPropertyById(int id) {
        botPropertyRepository.deleteById(id);
    }

    public BotProperty getBotProperties() throws NoSuchBotPropertyFound {
        return getBotPropertyById(1);
    }
}
