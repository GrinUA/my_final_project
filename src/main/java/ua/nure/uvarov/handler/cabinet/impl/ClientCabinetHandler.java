package ua.nure.uvarov.handler.cabinet.impl;

import ua.nure.uvarov.bean.OrderBean;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.handler.cabinet.PersonalCabinetHandler;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientCabinetHandler implements PersonalCabinetHandler {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        User user  = (User)req.getSession().getAttribute(Parameters.S_USER);
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(Parameters.ORDER_SERVICE);
        List<OrderBean> list = orderService.getUserOrders(user);
        req.setAttribute(Parameters.USER_ORDERS, list);
    }
    }



