package ua.nure.uvarov.web.controller;

import org.apache.log4j.Logger;
import ua.nure.uvarov.constants.Messages;
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
    private static final Logger LOG = Logger.getLogger(BookInfoController.class);

    private BookService bookService;
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET + "/bookInfo.do");
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

        LOG.info(Messages.LOG_FORWARD + "/bookInfo.do");
        req.getRequestDispatcher("WEB-INF/jsp/bookInfo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST + "/bookInfo.do");
        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        LOG.info( " Init -> /bookInfo.do");
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);
    }

}

