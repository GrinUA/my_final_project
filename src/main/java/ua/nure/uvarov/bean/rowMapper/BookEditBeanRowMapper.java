package ua.nure.uvarov.bean.rowMapper;


import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.mysql.BookGroupDaoImpl;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;

public class BookEditBeanRowMapper implements BeanRowMapper<BookGroup> {
    private Function<String, Genre> genreObjectFunc;
    BookGroupDao bookGroupDao = new BookGroupDaoImpl();

    public BookEditBeanRowMapper(Function<String, Genre> getGenreObjectFunc) {
        this.genreObjectFunc = getGenreObjectFunc;
    }
    public BookEditBeanRowMapper(){

    }
    @Override
    public BookGroup mapRow(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BookGroup bookGroup = new BookGroup();
     bookGroup.setId(request.getParameter(Parameters.ID));
       bookGroup.setName(request.getParameter(Parameters.NAME));
        bookGroup.setAuthor(request.getParameter(Parameters.AUTHOR));
        bookGroup.setEdition(request.getParameter(Parameters.EDITION));
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(request.getParameter(Parameters.PUBLICATION_DATE));
            bookGroup.setPublicationDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bookGroup.setGenre((Genre) request.getAttribute("genre"));
        bookGroup.setDescription(request.getParameter(Parameters.DESCRIPTION));
        bookGroup.setPrice(Double.parseDouble(request.getParameter(Parameters.PRICE)));
        return bookGroup;
    }
}
