package com.ddkirill.ratesbot.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "open-exchange-rates")
public record OpenExchangeRatesProperties(boolean enable, RatesAPI ratesAPI) {

    public record RatesAPI(

            String appId
    )
    {

    }
}
