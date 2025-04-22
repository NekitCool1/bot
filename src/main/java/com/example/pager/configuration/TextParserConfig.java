package com.example.pager.configuration;

import com.example.pager.bot.TextParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TextParserConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(TextParser textParser) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(textParser);
        return api;
    }
}
