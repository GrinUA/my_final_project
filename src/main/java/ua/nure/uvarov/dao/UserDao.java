package ua.nure.uvarov.dao;

import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.entity.UserRole;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    boolean isExist(String email);

    boolean getUserByEmailAndPassword(User user);

    User getUserByEmail(String email);

    boolean isBlocked(String email);

    boolean changeBlockStatus(String email,boolean blocked);
    boolean changeRole(String email, UserRole role);
    List<User>  getAll();
}
