package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UsersRepository usersRepository;

    public UserService() {
    }

    public void registerUser(Long telegramId) {

        if (!usersRepository.existsById(telegramId)) {
            Users user = new Users();
            user.setTelegramId(telegramId);
            usersRepository.save(user);
        }
    }
}
