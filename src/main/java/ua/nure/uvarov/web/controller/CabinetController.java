package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.entity.UserRole;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.handler.cabinet.impl.AdminCabinetHandler;
import ua.nure.uvarov.handler.cabinet.impl.ClientCabinetHandler;
import ua.nure.uvarov.handler.cabinet.impl.OperatorCabinetHandler;
import ua.nure.uvarov.handler.cabinet.PersonalCabinetHandler;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cabinet.do")
public class CabinetController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CabinetController.class);
    private Map<UserRole,PersonalCabinetHandler> handlerContainer;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET +"/cabinet.do");
        if (req.getSession().getAttribute(Parameters.S_USER) == null) {
            throw new NotFoundException();
        } else {
            User user = (User)req.getSession().getAttribute(Parameters.S_USER);
            handlerContainer.get(user.getRole()).execute(req,resp);
            LOG.info(Messages.LOG_FORWARD + "/cabinet.jsp");
            req.getRequestDispatcher("WEB-INF/jsp/cabinet.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST +"/cabinet.do");
        if (req.getSession().getAttribute(Parameters.S_USER) == null) {
            throw new NotFoundException();
        } else {
            LOG.info(Messages.LOG_FORWARD +"/cabinet.do");
            req.getRequestDispatcher("WEB-INF/jsp/cabinet.jsp").forward(req, resp);

        }
    }

        @Override
        public void init (ServletConfig config) throws ServletException {
            handlerContainer = new HashMap<>();
            handlerContainer.put(UserRole.ADMIN,new AdminCabinetHandler());
            handlerContainer.put(UserRole.CLIENT, new ClientCabinetHandler());
            handlerContainer.put(UserRole.OPERATOR, new OperatorCabinetHandler());
        }
    }

