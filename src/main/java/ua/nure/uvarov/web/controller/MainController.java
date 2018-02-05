package ua.nure.uvarov.web.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main.do")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("books.do").include(req, resp);

        /////!!!!!
        req.getSession().setAttribute("language", req.getParameter("lang"));

        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("WEB-INF/jsp/index.jsp");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }
}

