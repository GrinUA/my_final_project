package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.entity.UserRole;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.handler.AdminCabinetFindHandler;
import ua.nure.uvarov.handler.FindHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/find.do")
public class FindController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(FindController.class);
    private Map<UserRole, FindHandler> handlerContainer;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET + "/find.do");
        if (req.getSession().getAttribute(Parameters.S_USER) == null) {
            throw new NotFoundException();
        } else {
            User user = (User) req.getSession().getAttribute(Parameters.S_USER);
            handlerContainer.get(user.getRole()).execute(req, resp);

        }
    }
        @Override
        public void init (ServletConfig config) throws ServletException {
            LOG.info("Init -> /find.do");
            handlerContainer = new HashMap<>();
            handlerContainer.put(UserRole.ADMIN, new AdminCabinetFindHandler());
        }

    }

