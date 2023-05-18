package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // Чтобы выполнить добавление 3-х сравниваемых валют нужно -> проверить пуст ли массив у пользователя,
    // если да, то мы просто добаляем новую валюту, если нет, то мы проверяем содержиться ли добавляемая валюта в
    // в массиве пользователя, если содержиться то, пропускаем данный элемент и возвращаем false.
    @Override
    public void setCompareCurrency(Long telegramId, String aliasCurrency) {
        Optional<Users> optionalUser = usersRepository.findById(telegramId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            List<String> comparedCurrency = user.getComparedCurrency();
            if (comparedCurrency.isEmpty()) {
                comparedCurrency.add(aliasCurrency);
            }
            if (!comparedCurrency.contains(aliasCurrency)) {
                comparedCurrency.add(aliasCurrency);
            }
            usersRepository.save(user);
        }
    }

    @Override
    public int setFirstCompareCurrency(Long telegramId, String aliasCurrency) {

        Users user = userInfo.getUser(telegramId);
        List<String> compareCurrencyList = new ArrayList<>();
        compareCurrencyList.add(aliasCurrency);
        user.setComparedCurrency(compareCurrencyList);
        usersRepository.save(user);
        return 1;
    }
}
