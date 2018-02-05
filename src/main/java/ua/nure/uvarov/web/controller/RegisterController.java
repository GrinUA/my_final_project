package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.RegisterService;
import ua.nure.uvarov.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/register.do")
public class RegisterController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RegisterController.class);

    private RegisterService registrationService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        LOG.info("Init ->  /register.do");
        registrationService = new RegisterService();
        userService = (UserService) getServletContext().getAttribute(Parameters.USER_SERVICE);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET + "/register.do");

        registrationService.setBean(req);
        registrationService.setMap(req);

        LOG.info(Messages.LOG_FORWARD + "/register.jsp");

        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST + "/register.do");

        HttpSession session = req.getSession();
        Map<String, String> errors = registrationService.getErrorMap(req,userService);
        if (errors.isEmpty()) {
            User user = registrationService.getUser(req);
            userService.create(user);
            session.setAttribute("success_message","Registration successed, please login to confirm");

            LOG.info(Messages.LOG_REDIRECT + " /login.do");

            resp.sendRedirect("login.do");
        } else {
            session.setAttribute(Parameters.S_BEAN, registrationService.getUserBean(req));
            session.setAttribute(Parameters.S_ERRORS, errors);

            LOG.info(Messages.LOG_REDIRECT + " /register.jsp");

            resp.sendRedirect("register.jsp");
        }

    }


}