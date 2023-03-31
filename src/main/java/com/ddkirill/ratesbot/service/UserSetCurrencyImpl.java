package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Currency;
import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSetCurrencyImpl implements SetCurrencyForUser {

    private final UsersRepository usersRepository;

    public UserSetCurrencyImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void setBaseCurrency(Long telegramId, String baseCurrency) {

        Optional<Users> optionalUsers = usersRepository.findById(telegramId);
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
            user.getCurrencyList().add(new Currency(aliasCurrency));
            usersRepository.save(user);
        }
    }
}
