package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import com.ddkirill.ratesbot.service.interfaces.UserInfo;
import com.ddkirill.ratesbot.service.interfaces.UserTimer;
import org.springframework.stereotype.Service;

import java.sql.Time;

@Service
public class UserService implements UserTimer {

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

    @Override
    public void setTime(Long userId, Time time) {
        Users user = userInfo.getUser(userId);
        user.setNotificationTime(time);
        usersRepository.save(user);
    }

}
