package com.ddkirill.ratesbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram")
public record BotProperties(boolean enable, Bot bot) {

        public record Bot(
                String token,
                String username
        ) {

        }
}

