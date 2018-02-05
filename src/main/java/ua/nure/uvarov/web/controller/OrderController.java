package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.services.OrderService;
import ua.nure.uvarov.services.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order.do")
public class OrderController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(OrderController.class);

    private OrderService orderService;
    private User user;
    private String bookGroup;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST + "/order.do");
        user = (User) req.getSession().getAttribute(Parameters.S_USER);
        bookGroup = req.getParameter(Parameters.BOOK_ARTICUL);
        String guId = orderService.createOrder(user, bookGroup);
        if (guId != null) {
            LOG.info(Messages.LOG_REDIRECT + "/cabinet.do?activeTab=default");
            resp.sendRedirect("cabinet.do?activeTab=default");
        } else {
            LOG.error("Order not created!");
            throw new NotFoundException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET + "/order.do");
        String guid = req.getParameter("key");
        req.setAttribute("key", guid);
        LOG.info(Messages.LOG_FORWARD + "/confirm.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/confirm.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        LOG.info("Init -> /order.do");
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);
    }
}
