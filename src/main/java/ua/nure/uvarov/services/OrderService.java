package ua.nure.uvarov.services;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.User;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getUserOrders(int id);
    Map<String,Integer> getDataAboutOrderedBooks(String groupId);
    Order createOrder(User user, int idBook);
}
