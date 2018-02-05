package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.OrderStatus;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.entity.UserRole;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.handler.order.OrderStatusChangeHandler;
import ua.nure.uvarov.handler.order.OrderStatusChangeToCancel;
import ua.nure.uvarov.handler.order.OrderStatusChangeToClose;
import ua.nure.uvarov.handler.order.OrderStatusChangeToOpen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/orderStatus.do")
public class OrderStatusChangeController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(OrderStatusChangeController.class);

    private Map<OrderStatus, OrderStatusChangeHandler> handlerContainer;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST + "/orderStatus.do");
        User user = (User) (req.getSession().getAttribute(Parameters.S_USER));
        if (user.getRole().equals(UserRole.CLIENT)) {
            if (req.getParameter(Parameters.ORDER_STATUS).equals(OrderStatus.CANCELED.toString())) {
                handlerContainer.get(OrderStatus.CANCELED).execute(req, resp);
            } else throw new NotFoundException();
        }
        if (user.getRole().equals(UserRole.OPERATOR)) {
            handlerContainer.get(OrderStatus.valueOf(req.getParameter(Parameters.ORDER_STATUS))).execute(req, resp);
        }
        resp.sendRedirect("cabinet.do?activeTab=default");
    }


    @Override
    public void init() throws ServletException {
        LOG.info("Init -> /orderStatus.do");
        handlerContainer = new HashMap<>();
        handlerContainer.put(OrderStatus.CLOSED, new OrderStatusChangeToClose());
        handlerContainer.put(OrderStatus.OPEN, new OrderStatusChangeToOpen());
        handlerContainer.put(OrderStatus.CANCELED, new OrderStatusChangeToCancel());
    }
}
