package com.ddkirill.ratesbot.telegrambot.keyboard;

import com.ddkirill.ratesbot.enums.CurrencyAliasAndTitle;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardSelectCompareCurrency {

    private final String aliasUSD = CurrencyAliasAndTitle.USD.name();
    private final String aliasRUB = CurrencyAliasAndTitle.RUB.name();
    private final String aliasEUR = CurrencyAliasAndTitle.EUR.name();
    private final String aliasAED = CurrencyAliasAndTitle.AED.name();
    private final String aliasUZS = CurrencyAliasAndTitle.UZS.name();
    private final String aliasUAH = CurrencyAliasAndTitle.UAH.name();
    private final String aliasTRY = CurrencyAliasAndTitle.TRY.name();
    private final String aliasTJS = CurrencyAliasAndTitle.TJS.name();
    private final String aliasKZT = CurrencyAliasAndTitle.KZT.name();
    private final String aliasKRW = CurrencyAliasAndTitle.KRW.name();
    private final String aliasJPY = CurrencyAliasAndTitle.JPY.name();
    private final String aliasINR = CurrencyAliasAndTitle.INR.name();
    private final String aliasBTC = CurrencyAliasAndTitle.BTC.name();
    private final String aliasAZN = CurrencyAliasAndTitle.AZN.name();
    private final String aliasGEL = CurrencyAliasAndTitle.GEL.name();
    private final String aliasPLN = CurrencyAliasAndTitle.PLN.name();
    private final String aliasMXN = CurrencyAliasAndTitle.MXN.name();
    private final String aliasMNT = CurrencyAliasAndTitle.MNT.name();
    private final String aliasCOP = CurrencyAliasAndTitle.COP.name();
    private final String aliasCAD = CurrencyAliasAndTitle.CAD.name();

    public InlineKeyboardMarkup getKeyboard() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton usd = new InlineKeyboardButton();
        InlineKeyboardButton rub = new InlineKeyboardButton();
        InlineKeyboardButton eur = new InlineKeyboardButton();
        InlineKeyboardButton aed = new InlineKeyboardButton();
        InlineKeyboardButton uzs = new InlineKeyboardButton();
        InlineKeyboardButton uah = new InlineKeyboardButton();
        InlineKeyboardButton tRY = new InlineKeyboardButton();
        InlineKeyboardButton tjs = new InlineKeyboardButton();
        InlineKeyboardButton kzt = new InlineKeyboardButton();
        InlineKeyboardButton krw = new InlineKeyboardButton();
        InlineKeyboardButton jpy = new InlineKeyboardButton();
        InlineKeyboardButton inr = new InlineKeyboardButton();
        InlineKeyboardButton btc = new InlineKeyboardButton();
        InlineKeyboardButton azn = new InlineKeyboardButton();
        InlineKeyboardButton gel = new InlineKeyboardButton();
        InlineKeyboardButton pln = new InlineKeyboardButton();
        InlineKeyboardButton mxn = new InlineKeyboardButton();
        InlineKeyboardButton mnt = new InlineKeyboardButton();
        InlineKeyboardButton cop = new InlineKeyboardButton();
        InlineKeyboardButton cad = new InlineKeyboardButton();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        List<InlineKeyboardButton> fifthRow = new ArrayList<>();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        usd.setText(aliasUSD);
        usd.setCallbackData(aliasUSD);
        rub.setText(aliasRUB);
        rub.setCallbackData(aliasRUB);
        eur.setText(aliasEUR);
        eur.setCallbackData(aliasEUR);
        aed.setText(aliasAED);
        aed.setCallbackData(aliasAED);
        uzs.setText(aliasUZS);
        uzs.setCallbackData(aliasUZS);
        uah.setText(aliasUAH);
        uah.setCallbackData(aliasUAH);
        tRY.setText(aliasTRY);
        tRY.setCallbackData(aliasTRY);
        tjs.setText(aliasTJS);
        tjs.setCallbackData(aliasTJS);
        kzt.setText(aliasKZT);
        kzt.setCallbackData(aliasKZT);
        krw.setText(aliasKRW);
        krw.setCallbackData(aliasKRW);
        jpy.setText(aliasJPY);
        jpy.setCallbackData(aliasJPY);
        inr.setText(aliasINR);
        inr.setCallbackData(aliasINR);
        btc.setText(aliasBTC);
        btc.setCallbackData(aliasBTC);
        azn.setText(aliasAZN);
        azn.setCallbackData(aliasAZN);
        gel.setText(aliasGEL);
        gel.setCallbackData(aliasGEL);
        pln.setText(aliasPLN);
        pln.setCallbackData(aliasPLN);
        mxn.setText(aliasMXN);
        mxn.setCallbackData(aliasMXN);
        mnt.setText(aliasMNT);
        mnt.setCallbackData(aliasMNT);
        cop.setText(aliasCOP);
        cop.setCallbackData(aliasCOP);
        cad.setText(aliasCAD);
        cad.setCallbackData(aliasCAD);

        firstRow.add(rub);
        firstRow.add(eur);
        firstRow.add(aed);
        firstRow.add(uzs);
        secondRow.add(uah);
        secondRow.add(tRY);
        secondRow.add(tjs);
        secondRow.add(kzt);
        thirdRow.add(krw);
        thirdRow.add(jpy);
        thirdRow.add(inr);
        thirdRow.add(btc);
        fourthRow.add(azn);
        fourthRow.add(gel);
        fourthRow.add(pln);
        fourthRow.add(mxn);
        fifthRow.add(mnt);
        fifthRow.add(cop);
        fifthRow.add(cad);

        rowList.add(firstRow);
        rowList.add(secondRow);
        rowList.add(thirdRow);
        rowList.add(fourthRow);
        rowList.add(fifthRow);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

}
