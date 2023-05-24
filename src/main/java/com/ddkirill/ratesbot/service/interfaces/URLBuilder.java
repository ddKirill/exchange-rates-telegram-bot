package com.ddkirill.ratesbot.service.interfaces;

import java.util.List;
public interface URLBuilder {

    String buildOpenExchangeURL(String baseCurrency, List<String> compareCurrency);
}
