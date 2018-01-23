package ua.nure.uvarov.services;

import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.transaction.DBManager;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private DBManager dbManager;

    public OrderServiceImpl(OrderDao orderDao, DBManager dbManager) {
        this.orderDao = orderDao;
        this.dbManager = dbManager;
    }
}
