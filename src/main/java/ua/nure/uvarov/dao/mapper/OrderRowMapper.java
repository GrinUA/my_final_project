package ua.nure.uvarov.dao.mapper;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements EntityRowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setGuId(resultSet.getString(Parameters.GUID));
        order.setId(resultSet.getInt(Parameters.ID));
        order.setBookId(resultSet.getInt(Parameters.BOOK_ID));
        order.setUserId(resultSet.getInt(Parameters.USER_ID));
        resultSet.getDate(Parameters.BORROW_DATE);
        if(resultSet.wasNull()){order.setBorrowDate(null);}
        else {order.setBorrowDate(new java.util.Date(resultSet.getDate(Parameters.BORROW_DATE).getTime()));}
        order.setExpectedDate(new java.util.Date(resultSet.getDate(Parameters.EXPECTED_DATE).getTime()));
        order.setOrderDate(new java.util.Date(resultSet.getDate(Parameters.ORDER_DATE).getTime()));
        resultSet.getDate(Parameters.CLOSE_DATE);
        if(resultSet.wasNull()){order.setCloseDate(null);}
        else {order.setCloseDate(new java.util.Date(resultSet.getDate(Parameters.CLOSE_DATE).getTime()));}
        order.setPlace((resultSet.getInt(Parameters.PLACE)) == 1);
        order.setStatus(OrderStatus.valueOf(resultSet.getString(Parameters.STATUS)));
        resultSet.getDouble(Parameters.PENALTY);
        if(resultSet.wasNull()){order.setPenalty(0);}
        else {order.setPenalty(resultSet.getDouble(Parameters.PENALTY));}
        return order;

    }

    @Override
    public void unMap(PreparedStatement preparedStatement, Order order) throws SQLException {
        preparedStatement.setInt(1, order.getId());
        preparedStatement.setInt(2, order.getBookId());
        preparedStatement.setInt(3, order.getUserId());
        preparedStatement.setDate(4, new java.sql.Date(order.getBorrowDate().getTime()));
        preparedStatement.setDate(5, new java.sql.Date(order.getExpectedDate().getTime()));
        preparedStatement.setDate(6, new java.sql.Date(order.getOrderDate().getTime()));
        preparedStatement.setDate(7, new java.sql.Date(order.getCloseDate().getTime()));
        preparedStatement.setInt(8, order.isPlace() ? 1 : 0);
        preparedStatement.setDouble(9, order.getPenalty());

    }
}
