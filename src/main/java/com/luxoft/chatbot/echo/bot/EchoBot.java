package com.luxoft.chatbot.echo.bot;

import com.luxoft.chatbot.echo.entity.BotProperties;
import com.luxoft.chatbot.echo.exception.NoSuchBotPropertiesFound;
import com.luxoft.chatbot.echo.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class EchoBot extends TelegramLongPollingBot {

    private final BotProperties botProperties;

    @Autowired
    private EchoBot(BotService botService) throws NoSuchBotPropertiesFound {
        botProperties = botService.getBotProperties();
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
