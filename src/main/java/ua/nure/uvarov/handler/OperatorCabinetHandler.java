package ua.nure.uvarov.handler;

import ua.nure.uvarov.bean.OrderBean;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OperatorCabinetHandler implements PersonalCabinetHandler{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(Parameters.ORDER_SERVICE);
        List<OrderBean> list = orderService.getAllOrders();
        req.setAttribute(Parameters.USER_ORDERS, list);
    }
    }



