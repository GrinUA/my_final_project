package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.LoginService;
import ua.nure.uvarov.services.UserService;
import ua.nure.uvarov.util.ValidateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(LoginController.class);
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        LOG.info("Init -> /login.do");
        loginService = new LoginService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET + "/login.do");
        loginService.setMap(req);
        req.getSession().getAttribute(Parameters.S_ERRORS);
        LOG.info(Messages.LOG_FORWARD + "/login.jsp");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST + "/login.do");
        requestHandler(req, resp);
    }

    private void requestHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = loginService.getUser(req);
        UserService userService = (UserService) req.getServletContext().getAttribute(Parameters.USER_SERVICE);
        ValidateUtil validateUtil = new ValidateUtil();
        Map<String, String> errors = validateUtil.validateAuthorize(user);
        if (errors.isEmpty()) {
            User userResult = userService.tryToLogIn(user);
            if (userResult != null) {
                if (!userResult.isBlocked()) {
                    req.getSession().setAttribute(Parameters.S_USER, userResult);
                    LOG.info(Messages.LOG_REDIRECT + "/main.do");
                    resp.sendRedirect("main.do");
                    return;
                } else {
                    errors.put(Parameters.BLOCKED, Messages.BLOCKED_USER);
                }
            } else {
                errors.put(Parameters.S_ERRORS, Messages.INVALID_EMAIL);

            }
        }
        req.getSession().setAttribute(Parameters.S_ERRORS, errors);
        LOG.info(Messages.LOG_REDIRECT + "login.jsp");
        resp.sendRedirect("login.jsp");
    }
}




