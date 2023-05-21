package com.ddkirill.ratesbot.telegrambot;

import com.ddkirill.ratesbot.config.BotProperties;
import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import com.ddkirill.ratesbot.enums.PathTextMessage;
import com.ddkirill.ratesbot.service.*;
import com.ddkirill.ratesbot.telegrambot.keyboard.KeyboardSelectBaseCurrency;
import com.ddkirill.ratesbot.telegrambot.keyboard.KeyboardSelectCompareCurrency;
import com.ddkirill.ratesbot.telegrambot.keyboard.ReplyKeyboardMaker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class ExchangeRatesSmartBot {

    private final BotProperties botProperties;
    private final TelegramLongPollingBot telegramLongPollingBot;
    private final TelegramBotsApi telegramBotsApi;
    private final KeyboardSelectBaseCurrency keyboardSelectBaseCurrency;
    private final KeyboardSelectCompareCurrency keyboardSelectCompareCurrency;
    private final ReplyKeyboardMaker replyKeyboardMaker;
    private UserService userService;
    private CheckArray checkArray;
    private SetCurrencyForUser setCurrencyForUser;
    private ReadTXT readTXT;


    public ExchangeRatesSmartBot(BotProperties botProperties, KeyboardSelectBaseCurrency keyboardSelectBaseCurrency,
                                 KeyboardSelectCompareCurrency keyboardSelectCompareCurrency, ReplyKeyboardMaker replyKeyboardMaker, UserService userService,
                                 CheckArray checkArray, SetCurrencyForUser setCurrencyForUser, ReadTXT readTXT) throws TelegramApiException {
        this.botProperties = botProperties;
        this.keyboardSelectBaseCurrency = keyboardSelectBaseCurrency;
        this.keyboardSelectCompareCurrency = keyboardSelectCompareCurrency;
        this.replyKeyboardMaker = replyKeyboardMaker;
        this.userService = userService;
        this.checkArray = checkArray;
        this.setCurrencyForUser = setCurrencyForUser;
        this.readTXT = readTXT;
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
                String startMessage = PathTextMessage.SELECT_BASE_CURRENCY.getPath();
                String nonCommandMessage = PathTextMessage.NON_COMMAND.getPath();

                if (message.hasText()) {

                    if (CurrencyAliasAndTitle.contains(message.getText())) {
                        setCurrencyForUser.setCompareCurrency(chatId,message.getText());
                        sendTextAndReplyKeyboardMarkup(chatId, "Выбери валюту", replyKeyboardMaker.getKeyboard());
                    }

                    if (CurrencyAliasAndTitle.contains(message.getText()) && checkArray.checkArrayIfFull(chatId)) {
                        sendTextMessage(chatId, "Отлично, валюты добавлены! Теперь пора выбрать время для уведомлений");
                    }
                }

                if (message.isCommand()) {

                    if ("/start".equals(message.getText())) {
                        userService.registerUser(chatId);
                        sendTextMessageAndKeyboard(chatId, readTXT.readTextFile(startMessage),
                                keyboardSelectBaseCurrency.getKeyboard());
                    }

                    if ("/clear".equals(message.getText())) {
                        userService.deleteCompareCurrency(chatId);
                        sendTextMessage(chatId, "Валюты для сравнения очищены");
                    }

                } else if (message.isUserMessage() && !CurrencyAliasAndTitle.contains(message.getText())) {
                    sendTextMessage(chatId, readTXT.readTextFile(nonCommandMessage));
                }
            }

            if (update.hasCallbackQuery()) {

                CallbackQuery callbackQuery = update.getCallbackQuery();
                String callBackData = callbackQuery.getData();
                Long chatId = callbackQuery.getMessage().getChatId();
                Integer messageId = callbackQuery.getMessage().getMessageId();
                String selectCompareCurrency = PathTextMessage.SELECT_COMPARE_CURRENCY.getPath();


                if (callBackData.equals("USD")) {
                    setCurrencyForUser.setBaseCurrency(chatId, callBackData);
                    sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(selectCompareCurrency), replyKeyboardMaker.getKeyboard());
                }

            }
        }

        private void sendTextMessage(Long chatId, String text) {
            try {
                execute(SendMessage.builder()
                        .chatId(chatId)
                        .text(text)
                        .parseMode(ParseMode.HTML)
                        .build()
                );
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
                        .parseMode(ParseMode.HTML)
                        .build()
                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private void editTextMessage(Long chatId, String textNewMessage, Integer messageId, InlineKeyboardMarkup replyMarkup) {
            try {
                execute(EditMessageText.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .text(textNewMessage)
                        .replyMarkup(replyMarkup)
                        .parseMode(ParseMode.HTML)
                        .build()
                )
                ;
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private void sendTextAndReplyKeyboardMarkup(Long chatId, String textMessage, ReplyKeyboardMarkup replyKeyboardMarkup) {
            try {
                execute(SendMessage.builder()
                        .text(textMessage)
                        .chatId(chatId)
                        .replyMarkup(replyKeyboardMarkup)
                        .parseMode(ParseMode.HTML)
                        .build()
                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }

}
