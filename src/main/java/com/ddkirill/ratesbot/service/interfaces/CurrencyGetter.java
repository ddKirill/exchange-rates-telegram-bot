package com.ddkirill.ratesbot.service.interfaces;

import java.util.List;

public interface CurrencyGetter {

    String getBaseCurrency(Long chatId);
    List<String> getCompareCurrency(Long chatId);
}
