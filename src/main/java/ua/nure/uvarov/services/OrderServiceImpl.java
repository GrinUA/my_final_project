package ua.nure.uvarov.services;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.entity.Book;
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
    private BookDao bookDao;
    private DBManager dbManager;

    public OrderServiceImpl(OrderDao orderDao, BookGroupDao bookGroupDao, BookDao bookDao, DBManager dbManager) {
        this.orderDao = orderDao;
        this.bookGroupDao = bookGroupDao;
        this.dbManager = dbManager;
        this.bookDao = bookDao;
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
        Integer availableCount = dbManager.execute(() -> bookGroupDao.getBookCountByState(true, groupId));
        orderedBooks.put(Parameters.AVAILABLE_BOOK_COUNT, availableCount);
        return orderedBooks;
    }

    @Override
    public String createOrder(User user, String bookGroup) {
        return dbManager.execute(() -> {
            if(bookGroupDao.isExist(bookGroup)){
            Book book = bookDao.availableBookFromGroupId(bookGroup);
            bookDao.updateStatus(false, book.getId());
            return orderDao.createOrderByUser(user, book);}
        return null;});
    }
}
