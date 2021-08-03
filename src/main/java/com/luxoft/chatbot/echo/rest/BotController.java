package com.luxoft.chatbot.echo.rest;

import com.luxoft.chatbot.echo.utils.Mapper;
import com.luxoft.chatbot.echo.dto.BotPropertyDTO;
import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertyFound;
import com.luxoft.chatbot.echo.service.BotService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"Rest for bots managing"})
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
    @ApiOperation("Создание нового бота")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = @ApiResponse(code = 201, message = "Created successfully"))
    public void saveBotProperty(@RequestBody @ApiParam("Модель данных бота (DTO)") BotPropertyDTO botPropertyDTO) {
        BotProperty botProperty = mapper.botPropertyDTOtoEntity(botPropertyDTO);
        botService.saveBotProperty(botProperty);
    }

    @GetMapping("/all")
    @ApiOperation("Список всех ботов")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Success"))
    public List<BotPropertyDTO> getAllBotProperties() {
        List<BotProperty> list = botService.getAllBotProperties();
        return list.stream()
                .map(e -> mapper.botPropertyEntityToDTO(e))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение бота по его id")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Success"))
    public BotPropertyDTO getBotPropertyById(@PathVariable @ApiParam("Id бота") int id) throws NoSuchBotPropertyFound {
        BotProperty botProperty = botService.getBotPropertyById(id);
        return mapper.botPropertyEntityToDTO(botProperty);
    }

    @GetMapping("/")
    @ApiOperation("Получение бота по имени")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Success"))
    public BotPropertyDTO getBotPropertyByName(@RequestParam(value = "name") @ApiParam("Имя бота") String name) throws NoSuchBotPropertyFound {
        BotProperty botProperty = botService.getBotPropertyByName(name);
        return mapper.botPropertyEntityToDTO(botProperty);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление бота по его id")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Success"))
    public void deleteBotPropertyById(@PathVariable @ApiParam("Id бота") int id) {
        botService.deleteBotPropertyById(id);
    }

    @DeleteMapping("/")
    @ApiOperation("Удаление бота по имени")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Success"))
    public void deleteBotPropertyByName(@RequestParam(value = "name") @ApiParam("Имя бота") String name) {
        botService.deleteBotPropertyByName(name);
    }
}
