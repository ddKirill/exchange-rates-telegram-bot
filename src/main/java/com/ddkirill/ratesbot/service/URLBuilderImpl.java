package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.service.interfaces.URLBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class URLBuilderImpl implements URLBuilder {

    @Value("${open-exchange-rates.api.url}")
    private String openExchangeURL;
    @Value("${open-exchange-rates.api.app-id}")
    private String appId;

    public URLBuilderImpl() {
    }

    @Override
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
