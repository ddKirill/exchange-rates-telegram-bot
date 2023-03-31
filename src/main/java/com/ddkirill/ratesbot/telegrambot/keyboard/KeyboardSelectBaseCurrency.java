package com.ddkirill.ratesbot.telegrambot.keyboard;

import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardSelectBaseCurrency {

    public InlineKeyboardMarkup getKeyboard() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton usdButton = new InlineKeyboardButton();

        String currencyAlias = CurrencyAliasAndTitle.USD.getCurrencyTitle();
        usdButton.setText(currencyAlias);
        usdButton.setCallbackData(currencyAlias);

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(usdButton);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(firstRow);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

}
