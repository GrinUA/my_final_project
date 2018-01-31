package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.services.OrderService;
import ua.nure.uvarov.services.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order.do")
public class OrderController extends HttpServlet{
   private OrderService orderService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);

    }
}