package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import com.ddkirill.ratesbot.service.interfaces.UserInfo;
import org.springframework.stereotype.Service;

import java.sql.Time;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final UserInfo userInfo;

    public UserService(UsersRepository usersRepository, UserInfo userInfo) {
        this.usersRepository = usersRepository;
        this.userInfo = userInfo;
    }

    public void registerUser(Long telegramId) {

        if (!usersRepository.existsById(telegramId)) {
            Users user = new Users();
            user.setTelegramId(telegramId);
            usersRepository.save(user);
        }
    }

}
