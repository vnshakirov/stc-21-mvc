package ru.stc21.rmi.server;

import ru.stc21.internal.User;

import java.rmi.RemoteException;

public interface UserService {

    User createUser(String login, String password) throws RemoteException;

}
