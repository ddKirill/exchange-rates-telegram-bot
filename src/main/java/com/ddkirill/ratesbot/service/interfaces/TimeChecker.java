package com.ddkirill.ratesbot.service.interfaces;

import java.sql.Time;
import java.util.List;

public interface TimeChecker {

    List<Long> checkTimeForAllUsers(String currentTime);
}
