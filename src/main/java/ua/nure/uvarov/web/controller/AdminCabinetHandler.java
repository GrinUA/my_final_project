package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.UserService;
import ua.nure.uvarov.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCabinetHandler implements PersonalCainetHandler {


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
      UserService  userService = (UserService) req.getServletContext().getAttribute(Parameters.USER_SERVICE);
        List<User> list = userService.getAllUsers();
       req.setAttribute(Parameters.ALL_USERS, list);
    }
}
