package com.luxoft.chatbot.echo.dao;

import com.luxoft.chatbot.echo.entity.BotProperty;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BotPropertyRepository extends CrudRepository<BotProperty, Integer> {

    Optional<BotProperty> findBotPropertyByBotUserName(String name);

    void deleteBotPropertyByBotUserName(String name);
}
