package com.ddkirill.ratesbot.telegrambot.keyboard;

import com.ddkirill.ratesbot.enums.CountryFlagEnum;
import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompareCurrencyWithEmojiKeyboard {

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

        row1.add(new KeyboardButton(CurrencyAliasAndTitle.RUB.name() + " " + CountryFlagEnum.RUB.getIcon()));
        row1.add(new KeyboardButton(CurrencyAliasAndTitle.USD.name() + CountryFlagEnum.USD.getIcon()));
        row1.add(new KeyboardButton(CurrencyAliasAndTitle.EUR.name() + CountryFlagEnum.EUR.getIcon()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.AED.name() + CountryFlagEnum.AED.getIcon()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.UZS.name() + CountryFlagEnum.UZS.getIcon()));
        row2.add(new KeyboardButton(CurrencyAliasAndTitle.UAH.name() + CountryFlagEnum.UAH.getIcon()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.TRY.name() + CountryFlagEnum.TRY.getIcon()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.TJS.name() + CountryFlagEnum.TJS.getIcon()));
        row3.add(new KeyboardButton(CurrencyAliasAndTitle.KZT.name() + CountryFlagEnum.KZT.getIcon()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.KRW.name() + CountryFlagEnum.KRW.getIcon()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.JPY.name() + CountryFlagEnum.JPY.getIcon()));
        row4.add(new KeyboardButton(CurrencyAliasAndTitle.INR.name() + CountryFlagEnum.INR.getIcon()));
        row5.add(new KeyboardButton(CurrencyAliasAndTitle.BTC.name()));
        row5.add(new KeyboardButton(CurrencyAliasAndTitle.AZN.name() + CountryFlagEnum.AZN.getIcon()));
        row5.add(new KeyboardButton(CurrencyAliasAndTitle.GEL.name() + CountryFlagEnum.GEL.getIcon()));
        row6.add(new KeyboardButton(CurrencyAliasAndTitle.PLN.name() + CountryFlagEnum.PLN.getIcon()));
        row6.add(new KeyboardButton(CurrencyAliasAndTitle.MXN.name() + CountryFlagEnum.MXN.getIcon()));
        row6.add(new KeyboardButton(CurrencyAliasAndTitle.MNT.name() + CountryFlagEnum.MNT.getIcon()));
        row7.add(new KeyboardButton(CurrencyAliasAndTitle.COP.name() + CountryFlagEnum.COP.getIcon()));
        row7.add(new KeyboardButton(CurrencyAliasAndTitle.CAD.name() + CountryFlagEnum.CAD.getIcon()));

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
