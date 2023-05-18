package com.ddkirill.ratesbot.telegrambot.keyboard;

import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReplyKeyboardMaker {

    public ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();

        row1.add(new KeyboardButton(CurrencyAliasAndTitle.MXN.name()));
        row1.add(new KeyboardButton(CurrencyAliasAndTitle.TRY.name()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.GEL.name()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.JPY.name()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.BTC.name()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.RUB.name()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.KRW.name()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.UZS.name()));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}
