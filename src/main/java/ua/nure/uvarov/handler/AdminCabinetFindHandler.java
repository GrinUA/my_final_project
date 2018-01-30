package ua.nure.uvarov.handler;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCabinetFindHandler implements FindHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_USERS) ||
                req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_DEFAULT)) {
            userTab(req, resp);
        }
    }

    private void userTab(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = (UserService) req.getServletContext().getAttribute(Parameters.USER_SERVICE);
        List<User> list = userService.getAllUsers();
        req.setAttribute(Parameters.ALL_USERS, list);
        req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_USERS);
    }
}
