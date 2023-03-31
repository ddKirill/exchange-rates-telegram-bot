package com.ddkirill.ratesbot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({BotProperties.class, OpenExchangeRatesProperties.class})
public class ConfigurationProperties {

}
