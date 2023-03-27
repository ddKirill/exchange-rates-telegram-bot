package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;

import java.util.List;

public interface OpenExchangeClient {

    OpenExchangeRatesResponse requestFor3Currency(String baseCurrency, List<String> compareCurrency);
}
