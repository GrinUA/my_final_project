package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books.do")
public class BookCreateController extends HttpServlet {
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookGroup> bookGroups = bookService.getBookGroups();
        if(!bookGroups.isEmpty()){
            throw new IllegalArgumentException();
        }
        else
        {
            req.setAttribute(Parameters.BOOK_GROUP_LIST, bookGroups);
            req.getRequestDispatcher("/create_book.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        BookService bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
    }

    private Book mapToBook(HttpServletRequest request){

        return null;
    }
}
