package ua.nure.uvarov.handler.order;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.OrderStatus;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class OrderStatusChangeToCancel implements OrderStatusChangeHandler{
    OrderService orderService;
    BookService bookService;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        orderService = (OrderService) req.getServletContext().getAttribute(Parameters.ORDER_SERVICE);
        bookService = (BookService) req.getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        Order order = orderService.getOrderByGuid(req.getParameter(Parameters.GUID));
        order.setCloseDate(new Date(System.currentTimeMillis()));
        order.setStatus(OrderStatus.CANCELED);
        orderService.updateOrder(order);
        bookService.updateBookStatus(order.getBookId(), true);
    }
}
