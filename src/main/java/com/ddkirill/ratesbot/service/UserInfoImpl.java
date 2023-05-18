package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoImpl implements UserInfo{

    private UsersRepository usersRepository;

    public UserInfoImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users getUser(Long chatId) {
        Optional<Users> optionalUsers = usersRepository.findById(chatId);
        return optionalUsers.orElse(null);
    }

    @Override
    public boolean findUser(Long chatId) {
        Optional<Users> optionalUsers = usersRepository.findById(chatId);

        if (optionalUsers.isPresent()) {
            return true;
        } else return false;
    }
}
