package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.service.interfaces.TimeChecker;
import com.ddkirill.ratesbot.service.interfaces.UserInfo;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeCheckerImpl implements TimeChecker {

    private final UserInfo userInfo;

    public TimeCheckerImpl(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public List<Long> checkTimeForAllUsers(String currentTime) {
        List<Users> allUsersList = userInfo.getAllUsers();
        List<Long> userIdList = new ArrayList<>();
        for (Users user : allUsersList) {
            String notificationTime = user.getNotificationTime();
            Long telegramId = user.getTelegramId();
            if (notificationTime.equals(currentTime)) {
                userIdList.add(telegramId);
            }
        }
        return userIdList;
    }
}
