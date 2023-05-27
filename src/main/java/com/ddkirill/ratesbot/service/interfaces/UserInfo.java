package com.ddkirill.ratesbot.service.interfaces;

import com.ddkirill.ratesbot.entity.Users;

import java.util.List;

public interface UserInfo {

    Users getUser(Long chatId);
    boolean findUser(Long chatId);
    List<Users> getAllUsers();
}
