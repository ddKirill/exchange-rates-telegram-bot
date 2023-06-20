package com.ddkirill.ratesbot.telegrambot;

import com.ddkirill.ratesbot.config.BotProperties;
import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;
import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import com.ddkirill.ratesbot.enums.HoursEnum;
import com.ddkirill.ratesbot.enums.PathTextMessage;
import com.ddkirill.ratesbot.service.*;
import com.ddkirill.ratesbot.service.interfaces.CheckArray;
import com.ddkirill.ratesbot.service.interfaces.CurrencySetter;
import com.ddkirill.ratesbot.service.interfaces.TimeChecker;
import com.ddkirill.ratesbot.telegrambot.keyboard.CompareCurrencyKeyboard;
import com.ddkirill.ratesbot.telegrambot.keyboard.HoursKeyboard;
import com.ddkirill.ratesbot.telegrambot.keyboard.KeyboardSelectBaseCurrency;
import com.ddkirill.ratesbot.telegrambot.keyboard.MainMenuKeyboard;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ExchangeRatesSmartBot extends TelegramLongPollingBot {

    private final BotProperties botProperties;
    private final KeyboardSelectBaseCurrency keyboardSelectBaseCurrency;
    private final CompareCurrencyKeyboard compareCurrencyKeyboard;
    private UserCurrencyManager userCurrencyManager;
    private HoursKeyboard hoursKeyboard;
    private UserService userService;
    private CheckArray checkArray;
    private CurrencySetter currencySetter;
    private ReadTXT readTXT;
    private RequestManager requestManager;
    private ResponseBuilder responseBuilder;
    private TimeChecker timeChecker;
    private MainMenuKeyboard mainMenuKeyboard;


    public ExchangeRatesSmartBot(BotProperties botProperties, KeyboardSelectBaseCurrency keyboardSelectBaseCurrency,
                                 CompareCurrencyKeyboard compareCurrencyKeyboard,
                                 UserCurrencyManager userCurrencyManager, HoursKeyboard hoursKeyboard, UserService userService,
                                 CheckArray checkArray, CurrencySetter currencySetter, ReadTXT readTXT,
                                 RequestManager requestManager, ResponseBuilder responseBuilder, TimeChecker timeChecker,
                                 MainMenuKeyboard mainMenuKeyboard) {
        this.botProperties = botProperties;
        this.keyboardSelectBaseCurrency = keyboardSelectBaseCurrency;
        this.compareCurrencyKeyboard = compareCurrencyKeyboard;
        this.userCurrencyManager = userCurrencyManager;
        this.hoursKeyboard = hoursKeyboard;
        this.userService = userService;
        this.checkArray = checkArray;
        this.currencySetter = currencySetter;
        this.readTXT = readTXT;
        this.requestManager = requestManager;
        this.responseBuilder = responseBuilder;
        this.timeChecker = timeChecker;
        this.mainMenuKeyboard = mainMenuKeyboard;
    }

    @Override
    public String getBotToken() {
        return botProperties.bot().token();
    }
    @Override
    public String getBotUsername() {
        return botProperties.bot().username();
    }
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()){

                Message message = update.getMessage();
                Long chatId = message.getChatId();
                String startMessage = PathTextMessage.SELECT_BASE_CURRENCY.getPath();
                String nonCommandMessage = PathTextMessage.NON_COMMAND.getPath();
                String selectTimeText = PathTextMessage.SELECT_NOTIFICATION_TIME.getPath();
                String menuText = PathTextMessage.MAIN_MENU.getPath();
                String allCurrencyText = PathTextMessage.ALL_CURRENCIES_LIST.getPath();
                String clearCompareCurrencyText = PathTextMessage.CLEAR_COMPARE_CURRENCY.getPath();
                String arrowToDown = PathTextMessage.ARROW_TO_DOWN.getPath();
                String completedSetting = PathTextMessage.COMPLETED_SETTING.getPath();
                String ifCurrenciesIsPresent = PathTextMessage.IF_CURRENCIES_IS_PRESENT.getPath();
                String deleteNotificationTime = PathTextMessage.DELETE_NOTIFICATION_TIME.getPath();

                if (message.hasText()) {

                    if (CurrencyAliasAndTitle.contains(message.getText())) {
                        currencySetter.setCompareCurrency(chatId,message.getText());
                        sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(arrowToDown), compareCurrencyKeyboard.getKeyboard());
                    }

                    if (CurrencyAliasAndTitle.contains(message.getText()) && checkArray.checkArrayIfFull(chatId)) {
                        sendTextAndReplyKeyboardMarkup(chatId, new ReadTXT().readTextFile(selectTimeText), hoursKeyboard.getKeyboard());
                    }

                    if (HoursEnum.contains(message.getText())) {
                        userService.setNotificationTime(chatId,message.getText());
                        sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(completedSetting), mainMenuKeyboard.getKeyboard());
                    }

                    if ("Главное меню".equals(message.getText())) {
                        sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(menuText),mainMenuKeyboard.getKeyboard());
                    }


                }

                if (message.isCommand()) {

                    if ("/pick".equals(message.getText())) {
                        boolean checkUserCurrencies = userCurrencyManager.checkUserCurrencies(chatId);
                        if (checkUserCurrencies) {
                            sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(ifCurrenciesIsPresent), mainMenuKeyboard.getKeyboard());
                        } else {
                            sendTextMessageAndKeyboard(chatId, readTXT.readTextFile(startMessage),
                                    keyboardSelectBaseCurrency.getKeyboard());
                        }
                    }

                    if ("/clear".equals(message.getText())) {
                        userCurrencyManager.deleteCompareCurrency(chatId);
                        userCurrencyManager.deleteBaseCurrency(chatId);
                        sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(clearCompareCurrencyText), mainMenuKeyboard.getKeyboard());
                    }

                    if ("/clearTime".equals(message.getText())) {
                        userService.deleteNotificationTime(chatId);
                        sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(deleteNotificationTime), mainMenuKeyboard.getKeyboard());
                    }

                    if ("/start".equals(message.getText())) {
                        userService.registerUser(chatId);
                        sendTextMessage(chatId, readTXT.readTextFile(menuText));
                    }

                    if ("/myRates".equals(message.getText())) {
                        OpenExchangeRatesResponse userRates = requestManager.getUserRates(chatId);
                        if (userRates!=null) {
                            String response = responseBuilder.build(userRates);
                            sendTextMessage(chatId, response);
                        } else sendTextMessage(chatId, "Пока что, вы не имеете добавленных валют.");
                    }

                    if ("/currency".equals(message.getText())) {
                        sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(allCurrencyText), mainMenuKeyboard.getKeyboard());
                    }





//                } else if (message.isUserMessage() && !CurrencyAliasAndTitle.contains(message.getText())) {
//                    sendTextMessage(chatId, readTXT.readTextFile(nonCommandMessage));
                }
            }

            if (update.hasCallbackQuery()) {

                CallbackQuery callbackQuery = update.getCallbackQuery();
                String callBackData = callbackQuery.getData();
                Long chatId = callbackQuery.getMessage().getChatId();
                String selectCompareCurrency = PathTextMessage.SELECT_COMPARE_CURRENCY.getPath();


                if (callBackData.equals("USD")) {
                    currencySetter.setBaseCurrency(chatId, callBackData);
                    sendTextAndReplyKeyboardMarkup(chatId, readTXT.readTextFile(selectCompareCurrency), compareCurrencyKeyboard.getKeyboard());
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

        @Scheduled(cron = "0 0 * * * *")
        public void checkAndDistributionRates() {

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
            String timeToStringFormat = dateFormat.format(date);


            List<Long> userIdList = timeChecker.checkTimeForAllUsers(timeToStringFormat);

            for (Long userId : userIdList) {
                OpenExchangeRatesResponse userRates = requestManager.getUserRates(userId);
                String userRatesMessageText = responseBuilder.build(userRates);
                sendTextMessage(userId, userRatesMessageText);
                System.out.println("Курсы отправлены пользователю!");
            }
    }


}
