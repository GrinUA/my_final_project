package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Book;
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
    private OrderService orderService;
    private User user;
    private String bookGroup;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = (User) req.getSession().getAttribute(Parameters.S_USER);
        bookGroup = req.getParameter(Parameters.BOOK_ARTICUL);
        String guId = orderService.createOrder(user, bookGroup);
        if (guId != null) {
            resp.sendRedirect("order.do?key=" + guId);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String guid = req.getParameter("key");
req.setAttribute("key",guid);
        req.getRequestDispatcher("WEB-INF/jsp/confirm.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);
    }
}
