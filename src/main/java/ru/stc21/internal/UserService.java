package ru.stc21.internal;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<String, String> users = new ConcurrentHashMap<>();

    public void addUser(String login, String password) {
        users.put(login, password);
    }
}
