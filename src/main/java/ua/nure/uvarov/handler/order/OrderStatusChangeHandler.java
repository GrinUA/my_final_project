package ua.nure.uvarov.handler.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderStatusChangeHandler {
    void execute(HttpServletRequest req, HttpServletResponse resp);
}
