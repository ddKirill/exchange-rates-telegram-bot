package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.dto.OpenExchangeRatesResponse;
import com.ddkirill.ratesbot.entity.OldestRates;
import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import com.ddkirill.ratesbot.service.interfaces.OldestRatesHandler;
import com.ddkirill.ratesbot.service.interfaces.UserInfo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class OldestRatesHandlerImpl implements OldestRatesHandler {

    private final UserInfo userInfo;
    private final UsersRepository usersRepository;

    public OldestRatesHandlerImpl(UserInfo userInfo, UsersRepository usersRepository) {
        this.userInfo = userInfo;
        this.usersRepository = usersRepository;
    }

    @Override
    public void saveLatestCurrencyRate(OpenExchangeRatesResponse openExchangeRatesResponse, Long chatId) {
        Users user = userInfo.getUser(chatId);
        Map<String, Double> rates = openExchangeRatesResponse.getRates();
        String baseCurrencyAlias = openExchangeRatesResponse.getBase();
        Set<String> aliasSet = rates.keySet();
        Collection<Double> ratesCollection = rates.values();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        OldestRates oldestRates = new OldestRates(baseCurrencyAlias, aliasSet.stream().toList(), ratesCollection.stream().toList(), timestamp);
        user.setOldestRates(oldestRates);
        usersRepository.save(user);
    }

}
