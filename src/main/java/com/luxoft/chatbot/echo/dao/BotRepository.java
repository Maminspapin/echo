package com.luxoft.chatbot.echo.dao;

import com.luxoft.chatbot.echo.entity.BotProperty;
import org.springframework.data.repository.CrudRepository;

public interface BotRepository extends CrudRepository<BotProperty, Integer> {

}
