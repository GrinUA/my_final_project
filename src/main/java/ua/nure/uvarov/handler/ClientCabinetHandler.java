package ua.nure.uvarov.handler;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientCabinetHandler implements PersonalCabinetHandler{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        User user  = (User)req.getSession().getAttribute(Parameters.S_USER);
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(Parameters.USER_SERVICE);
        List<Order> list = orderService.getUserOrders(user.getId());
        req.setAttribute(Parameters.USER_ORDERS, list);
    }
    }

