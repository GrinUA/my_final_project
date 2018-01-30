package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userstatus.do")
public class UserStatusChangeController extends HttpServlet {
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(Parameters.USER_STATUS).equals(Parameters.USER_BLOCK)){
            userService.block(req.getParameter(Parameters.EMAIL));
        }
        if(req.getParameter(Parameters.USER_STATUS).equals(Parameters.USER_UNBLOCK)){
            userService.unblock(req.getParameter(Parameters.EMAIL));
        }
        req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_USERS);
            resp.sendRedirect("cabinet.do?activeTab=usersTab");
    }

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(Parameters.USER_SERVICE);
    }
}
