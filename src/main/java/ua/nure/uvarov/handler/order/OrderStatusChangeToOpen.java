package ua.nure.uvarov.handler.order;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.OrderStatus;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class OrderStatusChangeToOpen implements OrderStatusChangeHandler{
    OrderService orderService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        orderService = (OrderService) req.getServletContext().getAttribute(Parameters.ORDER_SERVICE);
        Order order = orderService.getOrderByGuid(req.getParameter(Parameters.GUID));
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setStatus(OrderStatus.OPEN);
        order.setExpectedDate(new Date(System.currentTimeMillis() + Parameters.ONE_MONTH));
        orderService.updateOrder(order);
    }
}
