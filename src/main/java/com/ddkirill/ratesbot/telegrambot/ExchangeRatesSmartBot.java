package com.ddkirill.ratesbot.telegrambot;

import com.ddkirill.ratesbot.config.BotProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Component
public class ExchangeRatesSmartBot {

    private final BotProperties botProperties;
    private final TelegramLongPollingBot telegramLongPollingBot;
    private final TelegramBotsApi telegramBotsApi;

    public ExchangeRatesSmartBot(BotProperties botProperties) throws TelegramApiException {
        this.botProperties = botProperties;
        this.telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        this.telegramLongPollingBot = new MyBot(botProperties.bot().token());
        this.telegramBotsApi.registerBot(telegramLongPollingBot);
    }


    class MyBot extends TelegramLongPollingBot {

        public MyBot(String botToken) {
            super(botToken);
        }

        @Override
        public String getBotUsername() {
            return botProperties.bot().username();
        }
        @Override
        public void onUpdateReceived(Update update) {

            var chatId = update.getMessage().getChatId();

            if (update.hasMessage()) {
                sendTextMessage(chatId, "hi");
            }
        }



        private void sendTextMessage(Long chatId, String text) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(text);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }


    }

}
