package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dto.ButtonDTO;
import com.luxoft.chatbot.echo.dto.KeyboardDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardService {

    private final WebClient webClient;

    @Getter
    private final ReplyKeyboardMarkup menu;

    @Autowired
    public KeyboardService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8082").build();

        menu = new ReplyKeyboardMarkup();
        menu.setSelective(true);
        menu.setResizeKeyboard(true);
        menu.setOneTimeKeyboard(false);
    }

    public ReplyKeyboardMarkup getMainMenuKeyboard() {

        // TODO при первом обращении пропускает doOnSuccess-стадию
        webClient
                .get()
                .uri("/api/keyboard/?name=test")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(KeyboardDTO.class)
                .doOnSuccess(e -> addButtonsToKeyboard(e.getButtons(), e.getButtonsInARow()))
                .subscribe();

        return menu;
    }

    private void addButtonsToKeyboard(List<ButtonDTO> buttons, int maxRowElements) {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        int rowSize = 0;

        for (ButtonDTO button : buttons) {
            KeyboardButton keyboardButton = new KeyboardButton(button.getName());
            if (rowSize++ % maxRowElements == 0) {
                rows.add(row);
                row = new KeyboardRow();
            }
            row.add(keyboardButton);
        }
        rows.add(row);

        menu.setKeyboard(rows);
    }

}
