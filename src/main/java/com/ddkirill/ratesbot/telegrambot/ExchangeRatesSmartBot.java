package com.ddkirill.ratesbot.telegrambot;

import com.ddkirill.ratesbot.config.BotProperties;
import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import com.ddkirill.ratesbot.enums.PathTextMessage;
import com.ddkirill.ratesbot.service.ReadTXT;
import com.ddkirill.ratesbot.service.SetCurrencyForUser;
import com.ddkirill.ratesbot.service.UserService;
import com.ddkirill.ratesbot.telegrambot.keyboard.KeyboardSelectBaseCurrency;
import com.ddkirill.ratesbot.telegrambot.keyboard.KeyboardSelectCompareCurrency;
import com.ddkirill.ratesbot.telegrambot.keyboard.ReplyKeyboardMaker;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
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
    private SetCurrencyForUser setCurrencyForUser;
    private ReadTXT readTXT;


    public ExchangeRatesSmartBot(BotProperties botProperties, KeyboardSelectBaseCurrency keyboardSelectBaseCurrency,
                                 KeyboardSelectCompareCurrency keyboardSelectCompareCurrency, ReplyKeyboardMaker replyKeyboardMaker, UserService userService,
                                 SetCurrencyForUser setCurrencyForUser, ReadTXT readTXT) throws TelegramApiException {
        this.botProperties = botProperties;
        this.keyboardSelectBaseCurrency = keyboardSelectBaseCurrency;
        this.keyboardSelectCompareCurrency = keyboardSelectCompareCurrency;
        this.replyKeyboardMaker = replyKeyboardMaker;
        this.userService = userService;
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

                if (message.isCommand()) {

                    if ("/start".equals(message.getText())) {
                        userService.registerUser(chatId);
                        sendTextMessageAndKeyboard(chatId, readTXT.readTextFile(startMessage),
                                keyboardSelectBaseCurrency.getKeyboard());
                    }
                } else if (message.isUserMessage()) {
                    sendTextMessage(chatId, readTXT.readTextFile(nonCommandMessage));
                }
            }

            if (update.hasCallbackQuery()) {

                CallbackQuery callbackQuery = update.getCallbackQuery();
                String callBackData = callbackQuery.getData();
                Long chatId = callbackQuery.getMessage().getChatId();
                Integer messageId = callbackQuery.getMessage().getMessageId();
                String selectCompareCurrency = PathTextMessage.SELECT_COMPARE_CURRENCY.getPath();
                String addedFirstCompareCurrency = PathTextMessage.ADDED_FIRST_COMPARE_CURRENCY.getPath();


                if (callBackData.equals("USD")) {
                    setCurrencyForUser.setBaseCurrency(chatId, callBackData);
                    sendTextMessageAndKeyboard(chatId, readTXT.readTextFile(selectCompareCurrency),
                            keyboardSelectCompareCurrency.getKeyboard());
                }

                else if (CurrencyAliasAndTitle.contains(callBackData)) {
                    int countAlias = setCurrencyForUser.setFirstCompareCurrency(chatId, callBackData);
                    sendTextAndReplyKeyboardMarkup(chatId, "bla bla", replyKeyboardMaker.getKeyboard());
                }



            }
        }

        private void sendTextMessage(Long chatId, String text) {
            try {
                execute(SendMessage.builder()
                        .chatId(chatId)
                        .text(text)
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
                        .build()
                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }

}
