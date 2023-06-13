package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;
import com.ddkirill.ratesbot.service.interfaces.CurrencyGetter;
import com.ddkirill.ratesbot.service.interfaces.OldestRatesHandler;
import com.ddkirill.ratesbot.service.interfaces.OpenExchangeClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestManager {

    private final OpenExchangeClient openExchangeClient;
    private final CurrencyGetter currencyGetter;
    private final OldestRatesHandler oldestRatesHandler;

    public RequestManager(OpenExchangeClient openExchangeClient, CurrencyGetter currencyGetter, OldestRatesHandler oldestRatesHandler) {
        this.openExchangeClient = openExchangeClient;
        this.currencyGetter = currencyGetter;
        this.oldestRatesHandler = oldestRatesHandler;
    }

    public OpenExchangeRatesResponse getUserRates(Long chatId) {
        String baseCurrency = currencyGetter.getBaseCurrency(chatId);
        List<String> compareCurrency = currencyGetter.getCompareCurrency(chatId);

        if (baseCurrency != null && compareCurrency.size() > 0) {
            OpenExchangeRatesResponse openExchangeRatesResponse = openExchangeClient.requestFor3Currency(baseCurrency, compareCurrency);
            oldestRatesHandler.saveLatestCurrencyRate(openExchangeRatesResponse,chatId);
            return openExchangeRatesResponse;
        } else return null;
    }

}
