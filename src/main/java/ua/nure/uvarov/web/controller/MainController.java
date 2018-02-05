package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main.do")
public class MainController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET + "/main.do");
        req.getRequestDispatcher("books.do").include(req, resp);
        LOG.info("Include -> /books.do");
        req.setAttribute(Parameters.FILTER_PARAMS, req.getSession().getAttribute(Parameters.FILTER_PARAMS));
        req.getSession().removeAttribute(Parameters.FILTER_PARAMS);
        LOG.info(Messages.LOG_FORWARD + "/index.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("WEB-INF/jsp/index.jsp");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        LOG.info("Init -> /main.do");
    }
}

