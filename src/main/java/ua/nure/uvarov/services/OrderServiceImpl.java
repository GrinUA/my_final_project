package ua.nure.uvarov.services;

import ua.nure.uvarov.bean.OrderBean;
import ua.nure.uvarov.bean.rowMapper.OrderBeanRowMapper;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.dao.UserDao;
import ua.nure.uvarov.entity.*;
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
    private UserDao userDao;

    public OrderServiceImpl(OrderDao orderDao, BookGroupDao bookGroupDao, BookDao bookDao, DBManager dbManager, UserDao userDao) {
        this.orderDao = orderDao;
        this.bookGroupDao = bookGroupDao;
        this.dbManager = dbManager;
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    @Override
    public List<OrderBean> getUserOrders(User user) {
        return dbManager.execute(() -> {
                    List<OrderBean> result = new ArrayList<>();
                    if (orderDao.isUserOrders(user.getId())) {
                        List<Order> list = orderDao.getUserOrders(user.getId());
                        list.forEach(order -> {
                            BookGroup bookGroup = bookGroupDao.getByBook(order.getBookId());
                            result.add(new OrderBeanRowMapper().mapRow(order, user, bookGroup));
                        });
                    }
                    return result;
                }
        );


    }


    public List<OrderBean> getAllOrders() {
        return dbManager.execute(() -> {
                    List<OrderBean> result = new ArrayList<>();
                    List<Order> list = orderDao.getAllOrders();
                    list.forEach(order -> {
                        BookGroup bookGroup = bookGroupDao.getByBook(order.getBookId());
                        User user = userDao.getById(order.getUserId());
                        result.add(new OrderBeanRowMapper().mapRow(order, user, bookGroup));
                    });
                    return result;
                }
        );
    }

    public Map<String, Double> getAllOrdersWithPenalty() {
        return dbManager.execute(() -> {
            Map<String, Double> map = new HashMap<>();
                    List<Order> list = orderDao.getAllOrders();
            for (int i = 0; i < list.size(); i++) {
                BookGroup bookGroup = bookGroupDao.getByBook(list.get(i).getBookId());
                if(map.containsKey(bookGroup.getName())){
                    map.put(bookGroup.getName(), map.get(bookGroup.getName()) + list.get(i).getPenalty());
            }
            else {
                    map.put(bookGroup.getName(), list.get(i).getPenalty());
                }

                }

            return map; });}



    @Override
    public Map<String, Integer> getCountOfAvailableBooks(String groupId) {
        Map<String, Integer> orderedBooks = new HashMap<>();
        Integer availableCount = dbManager.execute(() -> bookGroupDao.getBookCountByState(true, groupId));
        orderedBooks.put(Parameters.AVAILABLE_BOOK_COUNT, availableCount);
        return orderedBooks;
    }

    @Override
    public String createOrder(User user, String bookGroup) {
        return dbManager.execute(() -> {
            if (bookGroupDao.isExist(bookGroup)) {
                Book book = bookDao.availableBookFromGroupId(bookGroup);
                bookDao.updateStatus(false, book.getId());
                return orderDao.createOrderByUser(user, book);
            }
            return null;
        });
    }
    public Order getOrderByGuid(String guId) {
        return dbManager.execute(() -> orderDao.getOrderByGuid(guId)
        );
    }

    @Override
    public boolean updateOrder(Order order) {
        return  dbManager.execute(() ->
            orderDao.update(order));

    }


}
