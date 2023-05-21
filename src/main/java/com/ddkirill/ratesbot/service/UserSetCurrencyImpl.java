package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSetCurrencyImpl implements SetCurrencyForUser {

    private final UsersRepository usersRepository;
    private UserInfo userInfo;

    @Lazy
    public UserSetCurrencyImpl(UsersRepository usersRepository, UserInfo userInfo) {
        this.usersRepository = usersRepository;
        this.userInfo = userInfo;
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

}
