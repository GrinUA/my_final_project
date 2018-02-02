package ua.nure.uvarov.bean.rowMapper;


import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;

public class BookEditBeanRowMapper implements BeanRowMapper<BookGroup> {
    private Function<String, Genre> genreObjectFunc;

    public BookEditBeanRowMapper(Function<String, Genre> getGenreObjectFunc) {
        this.genreObjectFunc = getGenreObjectFunc;
    }
    public BookEditBeanRowMapper(){

    }
    @Override
    public BookGroup mapRow(HttpServletRequest request) {
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
        Genre genre;
        if (genreObjectFunc != null) {
            genre = genreObjectFunc.apply(request.getParameter(Parameters.GENRE_NAME));
        } else {
            genre = new Genre(request.getParameter(Parameters.GENRE_NAME));
        }
        bookGroup.setGenre(genre);
        bookGroup.setDescription(request.getParameter(Parameters.DESCRIPTION));
        bookGroup.setPrice(Double.parseDouble(request.getParameter(Parameters.PRICE)));
        return bookGroup;
    }
}
