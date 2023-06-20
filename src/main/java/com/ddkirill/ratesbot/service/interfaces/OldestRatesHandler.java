package com.ddkirill.ratesbot.service.interfaces;

import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;


public interface OldestRatesHandler {

    void saveLatestCurrencyRate(OpenExchangeRatesResponse openExchangeRatesResponse, Long chatId);


}
