package ua.nure.uvarov.handler.cabinet.impl;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.handler.cabinet.PersonalCabinetHandler;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.services.OrderService;
import ua.nure.uvarov.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class AdminCabinetHandler implements PersonalCabinetHandler {


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_USERS) ||
                req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_DEFAULT)) {
            userTab(req, resp);
        }
        if (req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_BOOKS)) {
            bookTab(req, resp);
        }
        if(req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_PENALTY)){
penaltyTab(req, resp);
        }

    }

    private void userTab(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = (UserService) req.getServletContext().getAttribute(Parameters.USER_SERVICE);
        List<User> list = userService.getAllUsers();
        req.setAttribute(Parameters.ALL_USERS, list);
        req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_USERS);
    }

    private void bookTab(HttpServletRequest req, HttpServletResponse resp) {
        BookService bookService = (BookService) req.getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        List<BookGroup> list = bookService.getBookGroups();
        req.setAttribute(Parameters.ALL_BOOKS, list);
        req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_BOOKS);
    }
    private void penaltyTab(HttpServletRequest req, HttpServletResponse resp) {
        BookService bookService = (BookService) req.getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(Parameters.ORDER_SERVICE);
        Map<String, Double> map = orderService.getAllOrdersWithPenalty();
        req.setAttribute(Parameters.PENALTY, map);
        req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_PENALTY);
    }
}
