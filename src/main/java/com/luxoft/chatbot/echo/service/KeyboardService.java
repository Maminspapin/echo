package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dto.ButtonDTO;
import com.luxoft.chatbot.echo.сlient.KeyboardWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardService {

    private final KeyboardWebClient keyboardWebClient;
    private final ReplyKeyboardMarkup menu;

    public KeyboardService(@Autowired KeyboardWebClient keyboardWebClient) {
        this.keyboardWebClient = keyboardWebClient;

        menu = new ReplyKeyboardMarkup();
        menu.setSelective(true);
        menu.setResizeKeyboard(true);
        menu.setOneTimeKeyboard(false);
    }

    public ReplyKeyboardMarkup getMainMenuKeyboard() {

        keyboardWebClient
                .getKeyboard()
                .filter(e -> e.getButtons() != null)
                .subscribe(e -> addButtonsToKeyboard(menu, e.getButtons(), e.getButtonsInARow()));

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
