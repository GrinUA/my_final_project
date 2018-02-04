package ua.nure.uvarov.web.controller;


import org.apache.log4j.Logger;
import ua.nure.uvarov.bean.FilterParams;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/searchBook.do")
public class SearchBookController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SearchBookController.class);

    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Call -> GET /searchBook.do");
        Map<String, String> errorMap = new HashMap<>();

        FilterParams filterParams = map(req, errorMap);
        errorMap.putAll(new ValidateUtil().validateFilterParams(filterParams));


        if (errorMap.isEmpty()) {
            List<BookGroup> bookGroups = bookService.getBookGroup(filterParams);
            req.setAttribute(Parameters.BOOK_GROUP_LIST, bookGroups);
            req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute(Parameters.ERRORS, errorMap);
            resp.sendRedirect("/main.do");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Call -> POST /searchBook.do");
        resp.sendRedirect("/searchBook.do");
    }

    @Override
    public void init() throws ServletException {
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
    }


    private FilterParams map(HttpServletRequest req, Map<String, String> errorMap) {
        return new FilterParams(
                req.getParameter(Parameters.NAME),
                req.getParameter(Parameters.AUTHOR),
                req.getParameter(Parameters.EDITION),
                req.getParameter(Parameters.PUBLICATION_DATE),
                req.getParameter(Parameters.GENRE)
        );
    }
}
