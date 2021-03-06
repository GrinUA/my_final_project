package ua.nure.uvarov.services;


import ua.nure.uvarov.dao.UserDao;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.entity.UserRole;
import ua.nure.uvarov.transaction.DBManager;
import java.util.List;


public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private DBManager dbManager;

    public UserServiceImpl(UserDao userDao, DBManager dbManager) {
        this.userDao = userDao;
        this.dbManager = dbManager;
    }

    @Override
    public boolean isExist(String email) {
        return dbManager.execute(() -> userDao.isExist(email));
    }

    @Override
    public int create(User user) {
        return dbManager.execute(() -> {
            user.setPassword(user.getPassword());
            return userDao.create(user);
        });
    }

    @Override
    public boolean isAuthorize(User user) {
        return dbManager.execute(() -> {

            user.setPassword(user.getPassword());
            return userDao.getUserByEmailAndPassword(user);
        });
    }

    @Override
    public User read(String email) {
        return dbManager.execute(() -> userDao.getUserByEmail(email));
    }


    @Override
    public boolean isBlocked(String email) {
        return dbManager.execute(() -> userDao.isBlocked(email));
    }


    @Override
    public boolean unblock(String email) {
        return dbManager.execute(() -> userDao.changeBlockStatus(email, false));
    }


    @Override
    public boolean block(String email) {
        return dbManager.execute(() ->
                userDao.changeBlockStatus(email, true));
    }

    @Override
    public boolean createOperator(String email) {
        return dbManager.execute(() ->
                userDao.changeRole(email, UserRole.OPERATOR));
    }

    @Override
    public boolean deleteOperator(String email) {
        return dbManager.execute(() ->
                userDao.changeRole(email, UserRole.CLIENT));
    }

    @Override
    public User tryToLogIn(User userBean) {
        return dbManager.execute(() -> {
            if (userDao.isExist(userBean.getEmail())) {
                User user = userDao.getUserByEmail(userBean.getEmail());
                if (user != null) {
                        userBean.setPassword(userBean.getPassword());
                        if (userDao.getUserByEmailAndPassword(userBean)) {
                            return user;

                    }
                }
            }
            return null;
        });
    }

    @Override
    public List<User> getAllUsers() {
        return dbManager.execute(()->userDao.getAll());
    }

    @Override
    public List<User> getUsersByParamether() {
        return dbManager.execute(()->userDao.getAll());
    }
}
