package ua.nure.uvarov.dao.mysql;
import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.UserDao;
import ua.nure.uvarov.dao.mapper.UserRowMapper;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.exceptions.DataBaseException;
import ua.nure.uvarov.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public boolean isExist(String email) {
        Connection connection = ThreadLockHandler.getConnection();
        boolean result;
        try (PreparedStatement st = connection.prepareStatement(MySQL.USER_BY_EMAIL)) {

            st.setString(1, email);
            result = st.executeQuery().next();

        } catch (SQLException e) {
            throw new DataBaseException(e);
        }

        return result;
    }

    @Override
    public boolean getUserByEmailAndPassword(User user) {
        Connection connection = ThreadLockHandler.getConnection();
        boolean result;
        try (PreparedStatement st = connection.prepareStatement(MySQL.USER_BY_EMAIL_AND_PASSWORD)) {
            st.setString(1, user.getEmail());
            st.setString(2, user.getPassword());
            result = st.executeQuery().next();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return result;
    }

    @Override
    public User getUserByEmail(String email) {
        User user;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.USER_BY_EMAIL)) {
            UserRowMapper userRowMapper = new UserRowMapper();
            st.setString(1, email);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            user = userRowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return user;
    }


    @Override
    public User getById(int id) {
        User user;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.USER_BY_ID)) {
            UserRowMapper userRowMapper = new UserRowMapper();
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            user = userRowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return user;
    }


    @Override
    public int create(User user) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            UserRowMapper userRowMapper = new UserRowMapper();
            userRowMapper.unMap(st, user);
            st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
            return user.getId();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public boolean isBlocked(String email) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.USERS_BLOCK_STATUS)) {
            st.setString(1, email);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            return resultSet.getInt(Parameters.BLOCKED) == 1;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public boolean changeBlockStatus(String email, boolean blocked) {
        Connection connection = ThreadLockHandler.getConnection();
        try(PreparedStatement st = connection.prepareStatement(MySQL.UPDATE_BLOCK_STATUS_BY_EMAIL)) {
            st.setInt(1, blocked?1:0);
            st.setString(2, email);
          st.execute();
            return true;
        }catch (SQLException e){
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_ALL_USERS)) {
            list = new ArrayList<>();
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast()) {
                resultSet.next();
              User user = new UserRowMapper().mapRow(resultSet);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return list;
    }



}
