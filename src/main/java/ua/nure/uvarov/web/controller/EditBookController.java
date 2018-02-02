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

@WebServlet("/editbook.do")
public class EditBookController extends HttpServlet{
    private BookService bookService;
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("articul");
        if(bookId == null || bookId.isEmpty()){
            throw new NotFoundException();
        }

        BookGroup bookGroup = bookService.getBookGroup(bookId);
        if(bookGroup == null){
            throw new NotFoundException();
        }
        req.setAttribute("bookInfo", bookGroup);
        req.getRequestDispatcher("WEB-INF/jsp/bookEdit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookGroup bookGroup = bookService.getBook(req);
        if(bookGroup != null) {
            bookService.updateBookGroup(bookGroup);
        }
        resp.sendRedirect("bookinfo.do?articul=" + bookGroup.getId());

    }

    @Override
    public void init() throws ServletException {
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);
    }
}
