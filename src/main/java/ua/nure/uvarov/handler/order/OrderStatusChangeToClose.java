package ua.nure.uvarov.handler.order;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.OrderStatus;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class OrderStatusChangeToClose implements OrderStatusChangeHandler {
    OrderService orderService;
    BookService bookService;


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        orderService = (OrderService) req.getServletContext().getAttribute(Parameters.ORDER_SERVICE);
        bookService = (BookService)req.getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        Order order = orderService.getOrderByGuid(req.getParameter(Parameters.GUID));
        double price = Double.valueOf(req.getParameter(Parameters.PRICE));
        setPenalty(price, order);
        order.setCloseDate(new Date(System.currentTimeMillis()));
        order.setStatus(OrderStatus.CLOSED);
        orderService.updateOrder(order);
        bookService.updateBookStatus(order.getBookId(), true);


    }
    private void setPenalty(Double price, Order order){
        long time = System.currentTimeMillis() - order.getExpectedDate().getTime();
        if (time > 0) {
            double penalty = price * Parameters.PERCENT / 100 * time / Parameters.ONE_DAY;
            if (penalty < price) {
                NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
                DecimalFormat formatter = (DecimalFormat)nf;
                formatter.applyPattern("####.##");
                order.setPenalty(Double.valueOf(formatter.format(penalty)));
            } else {
                order.setPenalty(price);
            }
        }
    }
}


