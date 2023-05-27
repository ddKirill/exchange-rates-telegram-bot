package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import com.ddkirill.ratesbot.service.interfaces.CurrencyGetter;
import com.ddkirill.ratesbot.service.interfaces.CurrencySetter;
import com.ddkirill.ratesbot.service.interfaces.UserInfo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCurrencyManager implements CurrencySetter, CurrencyGetter {

    private final UsersRepository usersRepository;
    private final UserInfo userInfo;

    @Lazy
    public UserCurrencyManager(UsersRepository usersRepository, UserInfo userInfo) {
        this.usersRepository = usersRepository;
        this.userInfo = userInfo;
    }

    public void deleteCompareCurrency(Long chatId) {
        Users user = userInfo.getUser(chatId);
        List<String> comparedCurrency = user.getComparedCurrency();
        comparedCurrency.clear();
        usersRepository.save(user);
    }

    public void deleteBaseCurrency(Long chatId) {
        Users user = userInfo.getUser(chatId);
        user.setBaseCurrency(null);
        usersRepository.save(user);
    }

    @Override
    public void setBaseCurrency(Long userId, String baseCurrency) {

        boolean infoUser = userInfo.findUser(userId);
        if (infoUser) {
            Users user = userInfo.getUser(userId);
            user.setBaseCurrency(baseCurrency);
            usersRepository.save(user);
        }
    }

    @Override
    public void setCompareCurrency(Long telegramId, String aliasCurrency) {

            Users user = userInfo.getUser(telegramId);
            List<String> comparedCurrency = user.getComparedCurrency();
            if (comparedCurrency.isEmpty()) {
                user.setComparedCurrency(aliasCurrency);
            }
            if (!comparedCurrency.contains(aliasCurrency) && comparedCurrency.size() < 3) {
                user.setComparedCurrency(aliasCurrency);
            }
            usersRepository.save(user);
    }

    @Override
    public String getBaseCurrency(Long chatId) {
        Users user = userInfo.getUser(chatId);
        String baseCurrency = user.getBaseCurrency();
        return baseCurrency;
    }

    @Override
    public List<String> getCompareCurrency(Long chatId) {
        Users user = userInfo.getUser(chatId);
        List<String> comparedCurrency = user.getComparedCurrency();
        return comparedCurrency;
    }
}
