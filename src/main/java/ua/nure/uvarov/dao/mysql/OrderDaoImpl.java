package ua.nure.uvarov.dao.mysql;

import ua.nure.uvarov.bean.OrderBean;
import ua.nure.uvarov.bean.rowMapper.OrderBeanRowMapper;
import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.dao.mapper.OrderRowMapper;
import ua.nure.uvarov.entity.*;
import ua.nure.uvarov.exceptions.DataBaseException;
import ua.nure.uvarov.transaction.ThreadLockHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String guId = String.valueOf(System.currentTimeMillis());
            st.setInt(1, book.getId());
            st.setInt(2, user.getId());
            st.setDate(3, new java.sql.Date(System.currentTimeMillis() + Parameters.ONE_DAY));
            st.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            st.setInt(5, 1);
            st.setString(6, OrderStatus.WAITING.toString());
            st.setString(7, guId);
            st.executeUpdate();
            return guId;

        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public List<Order> getUserOrders(int id) {
        List<Order> list;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.USER_ORDERS)) {
            list = new ArrayList<>();
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast()) {
                resultSet.next();
                Order order = new OrderRowMapper().mapRow(resultSet);
                list.add(order);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public Order getOrderByGuid(String guId) {
        return null;
    }

    public Order OrderDaoImpl(String guId) {
        Connection connection = ThreadLockHandler.getConnection();
        Order order = null;
        try (PreparedStatement st = connection.prepareStatement(MySQL.ORDER_BY_GUID)) {
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
         if(resultSet.next()) {
            order = new OrderRowMapper().mapRow(resultSet);
         }
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return order;
    }


    @Override
    public boolean isUserOrders(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        boolean result;
        try (PreparedStatement st = connection.prepareStatement(MySQL.USER_ORDERS)) {
            st.setInt(1, id);
            result = st.executeQuery().next();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return result;
    }

    @Override
    public boolean changeOrderStatusToClosed(Order order) {
            Connection connection = ThreadLockHandler.getConnection();
            try (PreparedStatement st = connection.prepareStatement(MySQL.UPDATE_ORDER_STATUS_TO_CLOSED)) {
                st.setDate(1, new java.sql.Date(System.currentTimeMillis()));

                st.execute();
                return true;
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }


    @Override
    public boolean changeOrderStatusToCancel(Order order) {
        return false;
    }

    @Override
    public boolean changeOrderStatusToOpen(Order order) {
        return false;
    }


}
