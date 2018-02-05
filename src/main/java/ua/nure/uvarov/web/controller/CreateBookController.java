package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createBook.do")
public class CreateBookController extends HttpServlet {
    private BookService bookService;
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Genre> genres = bookService.getGenres();
        req.setAttribute(Parameters.GENRE_LIST, genres);
        req.getRequestDispatcher("WEB-INF/jsp/bookCreate.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BookGroup bookGroup = bookService.getBook(req);
        if (bookGroup != null) {
           String guid= bookService.createBookGroup(bookGroup);
           bookService.createBookToGroup(Integer.valueOf(req.getParameter("count")), guid);
            resp.sendRedirect("/bookInfo.do?articul=" + guid);
        } else {
            throw new NotFoundException();
        }


    }

    @Override
    public void init() throws ServletException {
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);
    }
}
