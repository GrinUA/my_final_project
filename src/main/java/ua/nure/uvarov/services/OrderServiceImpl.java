package ua.nure.uvarov.services;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.transaction.DBManager;

import java.util.HashMap;
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
    public Map<String, Integer> getDataAboutOrderedBooks(String groupId) {
        Map<String, Integer> orderedBooks = new HashMap<>();
        Integer orderedCount = dbManager.execute(() -> orderDao.getOrderCountForBooksGroup(groupId));
        orderedBooks.put(Parameters.ORDERED_BOOK_COUNT, orderedCount);
        Integer availableCount = dbManager.execute(() -> bookGroupDao.getBookCountByState(false));
        orderedBooks.put(Parameters.AVAILABLE_BOOK_COUNT, availableCount);
        return orderedBooks;
    }
}
