package com.luxoft.chatbot.echo.rest;

import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertyFound;
import com.luxoft.chatbot.echo.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bot")
public class BotController {

    private BotService botService;

    @Autowired
    private void setBotPropertyRepository(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/new") // в swagger отображаем id
    public void saveBotProperty(@RequestBody BotProperty botProperty) {
        botService.saveBotProperty(botProperty);
    }

    @GetMapping("/all") // светим токен
    public List<BotProperty> getAllBotProperties() {
        return botService.getAllBotProperties();
    }

    @GetMapping("/{id}")
    public BotProperty getBotPropertyById(@PathVariable int id) throws NoSuchBotPropertyFound {
        return botService.getBotPropertyById(id);
    }

    @GetMapping("/")
    public BotProperty getBotPropertyByName(@RequestParam(value = "name") String name) throws NoSuchBotPropertyFound {
        return botService.getBotPropertyByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteBotPropertyById(@PathVariable int id) {
        botService.deleteBotPropertyById(id);
    }

    @DeleteMapping("/")
    public void deleteBotPropertyByName(@RequestParam(value = "name") String name) {
        botService.deleteBotPropertyByName(name);
    }
}
