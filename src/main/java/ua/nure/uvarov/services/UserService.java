package ua.nure.uvarov.services;


import ua.nure.uvarov.entity.User;

import java.util.List;

public interface UserService {

    boolean isExist(String email);

    int create(User user);

    boolean isAuthorize(User user);

    User read(String email);

    User read(int id);

    boolean isBlocked(String email);

    boolean unblock(String email);

    boolean block(String email);

    User tryToLogIn(User userBean);

    List<User> getAllUsers();

    List<User> getUsersByParamether();

}
