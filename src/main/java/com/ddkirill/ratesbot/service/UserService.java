package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import com.ddkirill.ratesbot.service.interfaces.UserInfo;
import org.springframework.stereotype.Service;

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

    public void setNotificationTime(Long chatId, String notificationTime) {
        Users user = userInfo.getUser(chatId);
        user.setNotificationTime(notificationTime);
        usersRepository.save(user);
    }

    public void deleteNotificationTime(Long chatId) {
        Users user = userInfo.getUser(chatId);
        user.setNotificationTime(null);
    }

}
