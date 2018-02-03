package ua.nure.uvarov.dao.mapper;


import com.mysql.jdbc.StringUtils;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;
import ua.nure.uvarov.exceptions.AppException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.function.Function;

public class BookGroupRowMapper implements EntityRowMapper<BookGroup> {
    private Function<Integer, Genre> genreObjectFunc;

    public BookGroupRowMapper(Function<Integer, Genre> getGenreObjectFunc) {
        this.genreObjectFunc = getGenreObjectFunc;
    }

    public BookGroupRowMapper() {
    }

    @Override
    public BookGroup mapRow(ResultSet resultSet) throws SQLException {
        BookGroup bookGroup = new BookGroup();
        bookGroup.setId(resultSet.getString(Parameters.ID));
        bookGroup.setAuthor(resultSet.getString(Parameters.AUTHOR));
        bookGroup.setName(resultSet.getString(Parameters.NAME));
        bookGroup.setPublicationDate(new Date(resultSet.getDate(Parameters.PUBLICATION_DATE).getTime()));
        bookGroup.setEdition(resultSet.getString(Parameters.EDITION));
        bookGroup.setDescription(resultSet.getString(Parameters.DESCRIPTION));
        bookGroup.setPrice(resultSet.getDouble(Parameters.PRICE));

        Genre genre;
        if (genreObjectFunc != null) {
            genre = genreObjectFunc.apply(resultSet.getInt(Parameters.GENRE));
        } else {
            genre = new Genre(resultSet.getInt(Parameters.GENRE));
        }
        bookGroup.setGenre(genre);
        bookGroup.setImage(resultSet.getString(Parameters.IMAGE));
        return bookGroup;
    }

    @Override
    public void unMap(PreparedStatement preparedStatement, BookGroup book) throws SQLException {
        preparedStatement.setString(1, book.getId());
        preparedStatement.setString(2, book.getName());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getEdition());
        preparedStatement.setDate(5, new java.sql.Date(book.getPublicationDate().getTime()));
        preparedStatement.setString(6, book.getDescription());
        preparedStatement.setDouble(7, book.getPrice());
        preparedStatement.setInt(8, book.getGenre().getId());
        preparedStatement.setString(9,
                StringUtils.isNullOrEmpty(book.getImage()) ? Parameters.DEFAULT_IMAGE_VALUE : book.getImage());

    }
}
