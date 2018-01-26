package ua.nure.uvarov.dao.mysql;

import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.exceptions.DataBaseException;
import ua.nure.uvarov.transaction.ThreadLockHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {


    @Override
    public int getOrderCountForBooksGroup(String groupId) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.COUNT_ORDERS_FOR_BOOK_GROUP)) {
            st.setString(1, groupId);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            return resultSet.getInt(Parameters.COUNT);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }
}
