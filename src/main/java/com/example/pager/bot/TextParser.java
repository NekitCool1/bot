package com.example.pager.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TextParser extends TelegramLongPollingBot {
    public TextParser(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            String[] strings = text.split("\\. ");
            String name = strings[0];
            String price = strings[1];
            String url = strings[2];
            String response = "🔥ЗНОВУ В НАЯВНОСТІ🔥\n" +
                    "✨" + name + "\n" +
                    "📦 Оптова ціна: " + price + " грн\n" +
                    "🌐 Посилання: " + url;

            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "staffParser_Bot";
    }
}
