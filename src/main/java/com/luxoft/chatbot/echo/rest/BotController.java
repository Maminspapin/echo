package com.luxoft.chatbot.echo.rest;

import com.luxoft.chatbot.echo.utils.Mapper;
import com.luxoft.chatbot.echo.dto.BotPropertyDTO;
import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertyFound;
import com.luxoft.chatbot.echo.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bot")
public class BotController {

    private BotService botService;
    private Mapper mapper;

    @Autowired
    private void setBotPropertyRepository(BotService botService) {
        this.botService = botService;
    }

    @Autowired
    private void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping("/new")
    public void saveBotProperty(@RequestBody BotPropertyDTO botPropertyDTO) {
        BotProperty botProperty = mapper.botPropertyDTOtoEntity(botPropertyDTO);
        botService.saveBotProperty(botProperty);
    }

    @GetMapping("/all")
    public List<BotPropertyDTO> getAllBotProperties() {
        List<BotProperty> list = botService.getAllBotProperties();
        return list.stream()
                .map(e -> mapper.botPropertyEntityToDTO(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BotPropertyDTO getBotPropertyById(@PathVariable int id) throws NoSuchBotPropertyFound {
        BotProperty botProperty = botService.getBotPropertyById(id);
        return mapper.botPropertyEntityToDTO(botProperty);
    }

    @GetMapping("/")
    public BotPropertyDTO getBotPropertyByName(@RequestParam(value = "name") String name) throws NoSuchBotPropertyFound {
        BotProperty botProperty = botService.getBotPropertyByName(name);
        return mapper.botPropertyEntityToDTO(botProperty);
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
