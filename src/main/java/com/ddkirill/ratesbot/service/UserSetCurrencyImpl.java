package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSetCurrencyImpl implements SetCurrencyForUser {

    private final UsersRepository usersRepository;

    @Lazy
    public UserSetCurrencyImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void setBaseCurrency(Long userId, String baseCurrency) {

        Optional<Users> optionalUsers = usersRepository.findById(userId);
        if (optionalUsers.isPresent()) {
            Users user = optionalUsers.get();
            user.setBaseCurrency(baseCurrency);
            usersRepository.save(user);
        }

    }

    @Override
    public void setCompareCurrency(Long telegramId, String aliasCurrency) {
        Optional<Users> optionalUser = usersRepository.findById(telegramId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.getComparedCurrency().add(aliasCurrency);
            usersRepository.save(user);
        }
    }
}
