package com.luxoft.chatbot.echo.bot;

import com.luxoft.chatbot.echo.entity.BotProperty;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertyFound;
import com.luxoft.chatbot.echo.service.BotService;
import com.luxoft.chatbot.echo.service.KeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class EchoBot extends TelegramLongPollingBot {

    private BotProperty botProperty;

    private final BotService botService;
    private final KeyboardService keyboardService;

    @Autowired
    private EchoBot(BotService botService, KeyboardService keyboardService) {
        this.botService = botService;
        this.keyboardService = keyboardService;
    }

    @PostConstruct
    void init() throws NoSuchBotPropertyFound {
        botProperty = botService.getBotProperties();
    }

    @Override
    public String getBotUsername() {
        return botProperty.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botProperty.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setReplyMarkup(keyboardService.getMainMenuKeyboard());

            Message msg = update.getMessage();
            String input = msg.getText();
            String output = input;

            // TODO сделать ответ по кнопке меню
//            Button button = buttonRepository.findButtonByName(input);
//            if (buttonRepository.findButtonByName(input) != null) {
//                output = button.getCallbackText();
//            }

            sendMessage.setChatId(msg.getChatId().toString());
            sendMessage.setText(output);

            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
