package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.dto.RatesResponse;

import java.util.List;

public interface OpenExchangeClient {

    RatesResponse requestFor3Currency(String baseCurrency, List<String> compareCurrency);
}
