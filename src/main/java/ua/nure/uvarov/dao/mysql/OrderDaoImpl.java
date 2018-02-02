package ua.nure.uvarov.dao.mysql;

import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.OrderStatus;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.exceptions.DataBaseException;
import ua.nure.uvarov.transaction.ThreadLockHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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

    @Override
    public String createOrderByUser(User user, Book book) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.CREATE_ORDER_BY_USER)) {
            String guId = UUID.randomUUID().toString();
            st.setInt(1, book.getId());
            st.setInt(2, user.getId());
            st.setDate(3, new java.sql.Date(System.currentTimeMillis() + Parameters.ONE_DAY));
            st.setString(4, OrderStatus.WAITING.toString());
            st.setString(5, guId);
            st.executeUpdate();
            return guId;

        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Order> getUserOrders(int id) {
        return null;
    }
}
