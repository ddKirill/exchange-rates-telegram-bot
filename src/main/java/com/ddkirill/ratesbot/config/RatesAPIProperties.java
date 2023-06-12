package com.ddkirill.ratesbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "open-exchange-rates")
public record RatesAPIProperties(API api) {

    public record API(
            String appId

    )

    {

    }
}
