package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;
import com.ddkirill.ratesbot.service.interfaces.CurrencyGetter;
import com.ddkirill.ratesbot.service.interfaces.OpenExchangeClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestManager {

    private final OpenExchangeClient openExchangeClient;
    private final CurrencyGetter currencyGetter;

    public RequestManager(OpenExchangeClient openExchangeClient, CurrencyGetter currencyGetter) {
        this.openExchangeClient = openExchangeClient;
        this.currencyGetter = currencyGetter;
    }

    public OpenExchangeRatesResponse getUserRates(Long chatId) {
        String baseCurrency = currencyGetter.getBaseCurrency(chatId);
        List<String> compareCurrency = currencyGetter.getCompareCurrency(chatId);

        if (baseCurrency != null && compareCurrency.size() > 0) {
            OpenExchangeRatesResponse openExchangeRatesResponse = openExchangeClient.requestFor3Currency(baseCurrency, compareCurrency);
            return openExchangeRatesResponse;
        } else return null;
    }

}
