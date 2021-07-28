package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dao.ButtonRepository;
import com.luxoft.chatbot.echo.entity.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardService {

    private ButtonRepository buttonRepository;

    @Autowired
    private void setButtonRepository(ButtonRepository buttonRepository) {
        this.buttonRepository = buttonRepository;
    }

    public ReplyKeyboardMarkup getMainMenuKeyboard() {

        ReplyKeyboardMarkup menu = new ReplyKeyboardMarkup();
        menu.setSelective(true);
        menu.setResizeKeyboard(true);
        menu.setOneTimeKeyboard(false);

        addButtonsToKeyboard(menu, 3);

        return menu;
    }

    private void addButtonsToKeyboard(ReplyKeyboardMarkup keyboard, int maxRowElements) {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        int rowSize = 0;

        for (Button button : buttonRepository.findAll()) {
            KeyboardButton keyboardButton = new KeyboardButton(button.getName());
            if (rowSize++ % maxRowElements == 0) {
                rows.add(row);
                row = new KeyboardRow();
            }
            row.add(keyboardButton);
        }
        rows.add(row);

        keyboard.setKeyboard(rows);
    }

}
