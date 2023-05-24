package com.ddkirill.ratesbot.telegrambot.keyboard;

import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompareCurrencyKeyboard {

    public ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();
        KeyboardRow row6 = new KeyboardRow();
        KeyboardRow row7 = new KeyboardRow();
        List<KeyboardRow> keyboard = new ArrayList<>();
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        row1.add(new KeyboardButton(CurrencyAliasAndTitle.RUB.name()));
        row1.add(new KeyboardButton(CurrencyAliasAndTitle.USD.name()));
        row1.add(new KeyboardButton(CurrencyAliasAndTitle.EUR.name()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.AED.name()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.UZS.name()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.UAH.name()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.TRY.name()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.TJS.name()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.KZT.name()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.KRW.name()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.JPY.name()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.INR.name()));
        row5.add(new KeyboardButton(CurrencyAliasAndTitle.BTC.name()));
        row5.add(new KeyboardButton(CurrencyAliasAndTitle.AZN.name()));
        row5.add(new KeyboardButton(CurrencyAliasAndTitle.GEL.name()));
        row6.add(new KeyboardButton(CurrencyAliasAndTitle.PLN.name()));
        row6.add(new KeyboardButton(CurrencyAliasAndTitle.MXN.name()));
        row6.add(new KeyboardButton(CurrencyAliasAndTitle.MNT.name()));
        row7.add(new KeyboardButton(CurrencyAliasAndTitle.COP.name()));
        row7.add(new KeyboardButton(CurrencyAliasAndTitle.CAD.name()));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);
        keyboard.add(row6);
        keyboard.add(row7);

        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}
