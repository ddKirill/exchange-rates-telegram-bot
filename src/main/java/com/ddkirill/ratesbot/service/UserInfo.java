package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;

public interface UserInfo {

    Users getUser(Long chatId);

    boolean findUser(Long chatId);
}
