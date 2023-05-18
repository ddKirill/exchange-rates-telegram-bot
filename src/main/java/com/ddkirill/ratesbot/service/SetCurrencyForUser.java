package com.ddkirill.ratesbot.service;

public interface SetCurrencyForUser {


    void setBaseCurrency(Long telegramId, String baseCurrency);

    void setCompareCurrency(Long telegramId, String aliasCurrency);

    int setFirstCompareCurrency(Long telegramId, String aliasCurrency);
}
