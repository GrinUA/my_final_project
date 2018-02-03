package ua.nure.uvarov.web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.OrderDao;
import ua.nure.uvarov.dao.UserDao;
import ua.nure.uvarov.dao.mysql.BookDaoImpl;
import ua.nure.uvarov.dao.mysql.BookGroupDaoImpl;
import ua.nure.uvarov.dao.mysql.OrderDaoImpl;
import ua.nure.uvarov.dao.mysql.UserDaoImpl;
import ua.nure.uvarov.exceptions.AppInitializationException;
import ua.nure.uvarov.services.*;
import ua.nure.uvarov.transaction.DBManager;
import ua.nure.uvarov.util.DomParser;


@WebListener
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        System.out.println(servletContext.getContextPath());
        DataSource dataSource;
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/librarydb");
        } catch (NamingException e) {
            throw new AppInitializationException();
        }

        DBManager dbManager = new DBManager(dataSource);


        UserDao userDao = new UserDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        BookGroupDao bookGroupDao = new BookGroupDaoImpl();


        UserService userService = new UserServiceImpl(userDao, dbManager);
        BookService bookService = new BookServiceImpl(bookDao, bookGroupDao, dbManager);
        OrderService orderService = new OrderServiceImpl(orderDao,bookGroupDao, bookDao, dbManager);


        servletContext.setAttribute(Parameters.USER_SERVICE, userService);
        servletContext.setAttribute(Parameters.BOOK_SERVICE, bookService);
        servletContext.setAttribute(Parameters.ORDER_SERVICE, orderService);

        String path = servletContext.getInitParameter("accessFile_path");
        DomParser domParser = new DomParser();
        domParser.parse(path);
        servletContext.setAttribute("accessMap",domParser.getAccessMap());
        servletContext.setAttribute("urlPatterns",domParser.getAllRestricUrl());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
