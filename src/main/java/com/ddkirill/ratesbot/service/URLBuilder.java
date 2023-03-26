package com.ddkirill.ratesbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class URLBuilder {

    @Value("${open-exchange-rates.api.url}")
    private String openExchangeURL;
    @Value("${open-exchange-rates.api.app-id}")
    private String appId;

    public URLBuilder() {
    }

    public String buildOpenExchangeURL(String baseCurrency, List<String> compareCurrency) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(openExchangeURL);
        stringBuilder.append("?app_id=");
        stringBuilder.append(appId);
        stringBuilder.append("&base=");
        stringBuilder.append(baseCurrency);
        stringBuilder.append("&symbols=");
        stringBuilder.append(String.join(",", compareCurrency));

        return stringBuilder.toString();
    }


}
