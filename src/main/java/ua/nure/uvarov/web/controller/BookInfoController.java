package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookInfo.do")
public class BookInfoController extends HttpServlet {
    private BookService bookService;
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter(Parameters.ARCTICUL);
        if(bookId == null || bookId.isEmpty()){
            throw new NotFoundException();
        }

        BookGroup bookGroup = bookService.getBookGroup(bookId);
        if(bookGroup == null){
            throw new NotFoundException();
        }
        req.setAttribute("bookInfo", bookGroup);
        req.setAttribute("booksData", orderService.getCountOfAvailableBooks(bookGroup.getId()));
        req.getRequestDispatcher("WEB-INF/jsp/bookInfo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);
    }

}

