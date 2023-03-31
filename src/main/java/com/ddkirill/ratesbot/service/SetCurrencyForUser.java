package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Currency;

public interface SetCurrencyForUser {


    void setBaseCurrency(Long telegramId, String baseCurrency);

    void setCompareCurrency(Long telegramId, String aliasCurrency);
}
