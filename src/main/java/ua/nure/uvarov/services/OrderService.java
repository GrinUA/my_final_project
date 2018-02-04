package ua.nure.uvarov.services;
import ua.nure.uvarov.bean.OrderBean;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.OrderStatus;
import ua.nure.uvarov.entity.User;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderBean> getUserOrders(User user);
    List<OrderBean> getAllOrders();
    Map<String,Integer> getCountOfAvailableBooks(String groupId);
    String createOrder(User user, String bookGroup);
    Order getOrderByGuid(String guid);
    boolean updateOrder(Order order);
}
