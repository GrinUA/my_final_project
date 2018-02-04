package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;
import ua.nure.uvarov.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books.do")
public class BookList extends HttpServlet {
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookGroup> bookGroups = bookService.getBookGroups();
        if (bookGroups.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            List<BookGroup> caruselBooks = new ArrayList<>();
            for (int i = 0; i < 3 && i<bookGroups.size(); i++) {
                caruselBooks.add(bookGroups.get(i));
            }
            req.getSession().setAttribute(Parameters.BOOK_GROUP_TOP_LIST, caruselBooks);
            req.setAttribute(Parameters.BOOK_GROUP_LIST, bookGroups);
        }

        List<Genre> genres = bookService.getGenres();
        req.getSession().setAttribute(Parameters.GENRE_LIST, genres);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        bookService = (BookService) getServletContext().getAttribute(Parameters.BOOK_SERVICE);
    }

    private Book mapToBook(HttpServletRequest request) {

        return null;
    }
}
