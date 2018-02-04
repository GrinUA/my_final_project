package ua.nure.uvarov.bean.rowMapper;

import ua.nure.uvarov.bean.OrderBean;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Order;
import ua.nure.uvarov.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class OrderBeanRowMapper implements BeanRowMapper<OrderBean> {

    @Override
    public OrderBean mapRow(HttpServletRequest request) {
        return null;
    }


    public OrderBean mapRow(Order order, User user, BookGroup bookGroup) {
        OrderBean orderBean = new OrderBean();
        orderBean.setGuId(order.getGuId());
        orderBean.setOrderDate(order.getOrderDate());
        orderBean.setBorrowDate(order.getBorrowDate());
        orderBean.setExpectedDate(order.getExpectedDate());
        orderBean.setCloseDate(order.getCloseDate());
        orderBean.setPlace(order.isPlace());
        orderBean.setUser(user);
        orderBean.setBookGroup(bookGroup);
        if (order.getPenalty() == 0) {
            if (orderBean.getCloseDate() == null) {
                long time = System.currentTimeMillis() - order.getExpectedDate().getTime();
                if (time > 0) {
                    double penalty = bookGroup.getPrice() * Parameters.PERCENT / 100 * time / Parameters.ONE_DAY;
                    if (penalty < bookGroup.getPrice()) {
                        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
                    DecimalFormat formatter = (DecimalFormat)nf;
                    formatter.applyPattern("####.##");
                        orderBean.setPenalty(Double.valueOf(formatter.format(penalty)));
                    } else {
                        orderBean.setPenalty(bookGroup.getPrice());
                    }
                }
            }
        }else orderBean.setPenalty(order.getPenalty());
        orderBean.setStatus(order.getStatus());

        return orderBean;


    }
}

