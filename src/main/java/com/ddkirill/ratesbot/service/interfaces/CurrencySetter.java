package com.ddkirill.ratesbot.service.interfaces;

public interface CurrencySetter {


    void setBaseCurrency(Long telegramId, String baseCurrency);

    void setCompareCurrency(Long telegramId, String aliasCurrency);
}
