package ua.nure.uvarov.dao.mapper;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.entity.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements EntityRowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Parameters.ID));
        user.setEmail(resultSet.getString(Parameters.EMAIL));
        user.setFirstName(resultSet.getString(Parameters.FIRST_NAME));
        user.setLastName(resultSet.getString(Parameters.LAST_NAME));
        user.setBlocked(resultSet.getInt(Parameters.BLOCKED)==1);
        user.setRole( UserRole.valueOf(resultSet.getString(Parameters.ROLE)));
        return user;
    }

    @Override
    public void unMap(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setInt(5,user.isBlocked()?1:0);
        preparedStatement.setString(6, String.valueOf((user.getRole())));
    }
}
