package ua.nure.uvarov.handler;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientCabinetHandler implements PersonalCabinetHandler{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(Parameters.USER_SERVICE);

    }
}
