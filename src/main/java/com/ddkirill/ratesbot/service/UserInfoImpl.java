package com.ddkirill.ratesbot.service;

import com.ddkirill.ratesbot.entity.Users;
import com.ddkirill.ratesbot.repository.UsersRepository;
import com.ddkirill.ratesbot.service.interfaces.CheckArray;
import com.ddkirill.ratesbot.service.interfaces.UserInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserInfoImpl implements UserInfo, CheckArray {

    private final UsersRepository usersRepository;

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

    @Override
    public List<Users> getAllUsers() {
        Iterable<Users> allUsersIterable = usersRepository.findAll();
        List<Users> usersList = new ArrayList<>();
        for (Users user : allUsersIterable) {
            usersList.add(user);
        }
        return usersList;
    }

    @Override
    public boolean checkArrayIfFull(Long chatId) {
        Users user = getUser(chatId);
        List<String> comparedCurrency = user.getComparedCurrency();
        if (comparedCurrency.size() == 3) {
            return true;
        } else return false;
    }
}
