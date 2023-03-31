package com.ddkirill.ratesbot.telegrambot;

import com.ddkirill.ratesbot.config.BotProperties;
import com.ddkirill.ratesbot.enums.TextMessage;
import com.ddkirill.ratesbot.service.SetCurrencyForUser;
import com.ddkirill.ratesbot.service.UserService;
import com.ddkirill.ratesbot.telegrambot.keyboard.KeyboardSelectBaseCurrency;
import com.ddkirill.ratesbot.telegrambot.keyboard.KeyboardSelectCompareCurrency;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class ExchangeRatesSmartBot {

    private final BotProperties botProperties;
    private final TelegramLongPollingBot telegramLongPollingBot;
    private final TelegramBotsApi telegramBotsApi;
    private final KeyboardSelectBaseCurrency keyboardSelectBaseCurrency;
    private final KeyboardSelectCompareCurrency keyboardSelectCompareCurrency;
    private UserService userService;
    private SetCurrencyForUser setCurrencyForUser;


    public ExchangeRatesSmartBot(BotProperties botProperties, KeyboardSelectBaseCurrency keyboardSelectBaseCurrency,
                                 KeyboardSelectCompareCurrency keyboardSelectCompareCurrency, UserService userService,
                                 SetCurrencyForUser setCurrencyForUser) throws TelegramApiException {
        this.botProperties = botProperties;
        this.keyboardSelectBaseCurrency = keyboardSelectBaseCurrency;
        this.keyboardSelectCompareCurrency = keyboardSelectCompareCurrency;
        this.userService = userService;
        this.setCurrencyForUser = setCurrencyForUser;
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

            if (update.hasMessage()){

                Message message = update.getMessage();
                Chat chat = message.getChat();
                Long chatId = message.getChatId();

                if (message.isCommand()) {

                    if ("/start".equals(message.getText())) {
                        userService.registerUser(chatId);
                        sendTextMessageAndKeyboard(chatId, TextMessage.SELECT_BASE_CURRENCY.getTextMessage(),
                                keyboardSelectBaseCurrency.getKeyboard());
                    }
                } else if (message.isUserMessage()) {
                    sendTextMessage(chatId, TextMessage.NON_COMMAND.getTextMessage());
                }
            }

            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String callBackData = callbackQuery.getData();
                Long chatId = callbackQuery.getMessage().getChatId();

                if (callBackData.equals("USD")) {
                    setCurrencyForUser.setBaseCurrency(chatId, callBackData);
                    sendTextMessageAndKeyboard(chatId, TextMessage.SELECT_BASE_CURRENCY.getTextMessage(),
                            keyboardSelectCompareCurrency.getKeyboard());

                }

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

        private void sendTextMessageAndKeyboard(Long chatId, String text, InlineKeyboardMarkup replyKeyboard) {
            try {
                execute(SendMessage.builder()
                        .chatId(chatId)
                        .text(text)
                        .replyMarkup(replyKeyboard)
                        .build()
                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }

}
