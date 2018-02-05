package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.UserRole;
import ua.nure.uvarov.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/operatorStatus.do")
public class OperatorStatusChangeController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(OperatorStatusChangeController.class);

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST + "/operatorStatus.do");

        if (UserRole.valueOf(req.getParameter(Parameters.ROLE)) == UserRole.CLIENT) {
            userService.createOperator(req.getParameter(Parameters.EMAIL));
        }
        if (UserRole.valueOf(req.getParameter(Parameters.ROLE)) == UserRole.OPERATOR) {
            userService.deleteOperator(req.getParameter(Parameters.EMAIL));
        }
        req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_USERS);

        LOG.info(Messages.LOG_REDIRECT + "/cabinet.do?activeTab=usersTab");

        resp.sendRedirect("cabinet.do?activeTab=usersTab");
    }

    @Override
    public void init() throws ServletException {
        LOG.info("Init -> /operatorStatus.do");
        userService = (UserService) getServletContext().getAttribute(Parameters.USER_SERVICE);
    }
}
