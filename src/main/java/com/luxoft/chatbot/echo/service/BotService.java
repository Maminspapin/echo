package com.luxoft.chatbot.echo.service;

import com.luxoft.chatbot.echo.dao.PropertyDAO;
import com.luxoft.chatbot.echo.entity.BotProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotService extends TelegramLongPollingBot {

    private final BotProperties botProperties;

    private BotService(PropertyDAO propertyDAO) {
        botProperties = propertyDAO.getBotProperty();
    }

    @Override
    public String getBotUsername() {
        return botProperties.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText(update.getMessage().getText());

            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
