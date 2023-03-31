package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.config.OpenExchangeRatesProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class URLBuilderImpl implements URLBuilder {

    @Value("${open-exchange-rates.api.url}")
    private String openExchangeURL;
    private OpenExchangeRatesProperties openExchangeRatesProperties;

    public URLBuilderImpl(OpenExchangeRatesProperties openExchangeRatesProperties) {
        this.openExchangeRatesProperties = openExchangeRatesProperties;
    }

    @Override
    public String buildOpenExchangeURL(String baseCurrency, List<String> compareCurrency) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(openExchangeURL);
        stringBuilder.append("?app_id=");
        stringBuilder.append(openExchangeRatesProperties.ratesAPI().appId());
        stringBuilder.append("&base=");
        stringBuilder.append(baseCurrency);
        stringBuilder.append("&symbols=");
        stringBuilder.append(String.join(",", compareCurrency));

        return stringBuilder.toString();
    }


}
