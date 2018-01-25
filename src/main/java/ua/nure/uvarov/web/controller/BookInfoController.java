package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookInfo.do")
public class BookInfoController extends HttpServlet {
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/bookInfo.jsp").forward(req,resp);
        //resp.sendRedirect("WEB-INF/jsp/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
    }

}

