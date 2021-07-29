package com.luxoft.chatbot.echo.rest;

import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bot")
public class BotController {

    private BotService botService;

    @Autowired
    private void setBotPropertyRepository(BotService botService) {
        this.botService = botService;
    }

    @GetMapping("/all") // светим токен
    public List<BotProperty> botProperties() {
        return botService.getAllBotPProperties();
    }
}
