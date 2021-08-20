package com.luxoft.chatbot.echo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Модель данных бота", description = "DTO")
@Data
@AllArgsConstructor
public class BotPropertyDTO {

    @ApiModelProperty(example = "test_bot", value = "Имя бота")
    @NotBlank(message = "Name of bot is mandatory")
    private String botUserName;

    @ApiModelProperty(example = "0123456789:AAAaAa0aAAAAAAAaaA00000aaAa0AAaAaaa", value = "Токен")
    @NotBlank(message = "Token of bot is mandatory")
    private String botToken;
}
