package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.entity.BotProperties;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertiesFound;

public interface BotService {

    BotProperties getBotProperties() throws NoSuchBotPropertiesFound;
}
