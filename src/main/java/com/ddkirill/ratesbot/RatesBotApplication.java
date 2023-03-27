package com.ddkirill.ratesbot;

import com.ddkirill.ratesbot.telegrambot.ExchangeRatesSmartBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class RatesBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesBotApplication.class, args);

	}

}
