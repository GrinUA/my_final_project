package ua.nure.uvarov.dao;

import ua.nure.uvarov.bean.OrderBean;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.User;

import java.util.List;

public interface OrderDao {



    int getOrderCountForBooksGroup(String groupId);
   String createOrderByUser(User user,Book book);
   List<Order> getAllOrders();
   List<Order> getUserOrders(int id);
   boolean isUserOrders(int id);
}
