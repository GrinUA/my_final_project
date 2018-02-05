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

@WebServlet("/editBook.do")
public class EditBookController extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(EditBookController.class);

    private BookService bookService;
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_GET + "/editBook.do");
        String bookId = req.getParameter("articul");
        if(bookId == null || bookId.isEmpty()){
            throw new NotFoundException();
        }

        BookGroup bookGroup = bookService.getBookGroup(bookId);
        if(bookGroup == null){
            throw new NotFoundException();
        }
        req.setAttribute("bookInfo", bookGroup);
        LOG.info(Messages.LOG_FORWARD + "/bookEdit.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/bookEdit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(Messages.LOG_POST + "/editBook.do");
        req.setCharacterEncoding("UTF-8");
        BookGroup bookGroup = bookService.getBook(req);
        if(bookGroup != null) {

            bookService.updateBookGroup(bookGroup);
            LOG.info(Messages.LOG_REDIRECT + "/editBook.do");
            resp.sendRedirect("bookInfo.do?articul=" + bookGroup.getId());
        }
        else {
            req.setAttribute("genre", "genre not found");
            LOG.info(Messages.LOG_REDIRECT + "/main.do");
            resp.sendRedirect("/main.do");
        }


    }

    @Override
    public void init() throws ServletException {
        LOG.info("Init -> /editBook.do");
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        orderService = (OrderService) getServletContext().getAttribute(Parameters.ORDER_SERVICE);
    }
}
