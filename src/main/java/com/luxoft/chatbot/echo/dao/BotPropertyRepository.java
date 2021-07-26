package com.luxoft.chatbot.echo.dao;

import com.luxoft.chatbot.echo.entity.BotProperties;
import org.springframework.data.repository.CrudRepository;

public interface BotPropertyRepository extends CrudRepository<BotProperties, Integer> {

}
