package com.ddkirill.ratesbot.service.interfaces;

import com.ddkirill.ratesbot.entity.Users;

public interface UserInfo {

    Users getUser(Long chatId);

    boolean findUser(Long chatId);
}
