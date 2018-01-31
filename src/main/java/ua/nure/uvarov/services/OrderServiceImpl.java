package ua.nure.uvarov.services;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.transaction.DBManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private BookGroupDao bookGroupDao;
    private DBManager dbManager;

    public OrderServiceImpl(OrderDao orderDao, BookGroupDao bookGroupDao, DBManager dbManager) {
        this.orderDao = orderDao;
        this.bookGroupDao = bookGroupDao;
        this.dbManager = dbManager;
    }

    @Override
    public List<Order> getUserOrders(int id) {
        List<Order> list = new ArrayList<>();
        return list;
    }

    @Override
    public Map<String, Integer> getDataAboutOrderedBooks(String groupId) {
        Map<String, Integer> orderedBooks = new HashMap<>();
        Integer orderedCount = dbManager.execute(() -> orderDao.getOrderCountForBooksGroup(groupId));
        orderedBooks.put(Parameters.ORDERED_BOOK_COUNT, orderedCount);
        Integer availableCount = dbManager.execute(() -> bookGroupDao.getBookCountByState(true,groupId));
        orderedBooks.put(Parameters.AVAILABLE_BOOK_COUNT, availableCount);
        return orderedBooks;
    }

    @Override
    public Order createOrder(User user, int idBook) {
        return null;
    }
}
