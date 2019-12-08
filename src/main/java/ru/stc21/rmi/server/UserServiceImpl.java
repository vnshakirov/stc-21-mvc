package ru.stc21.rmi.server;

import ru.stc21.internal.User;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(String login, String password) {
        User user = new User();
        user.setLogin(login + "login");
        user.setPassword(password + "password");
        user.setDate("date");
        return user;
    }
}
