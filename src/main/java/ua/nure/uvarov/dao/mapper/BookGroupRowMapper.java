package ua.nure.uvarov.dao.mapper;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookGroupRowMapper implements EntityRowMapper<BookGroup> {
    @Override
    public BookGroup mapRow(ResultSet resultSet) throws SQLException {
       BookGroup bookGroup = new BookGroup();
        bookGroup.setId(resultSet.getInt(Parameters.ID));
        bookGroup.setAuthor(resultSet.getString(Parameters.AUTHOR));
        bookGroup.setName(resultSet.getString(Parameters.NAME));
        bookGroup.setPublicationDate(new java.util.Date(resultSet.getDate(Parameters.PUBLICATION_DATE).getTime()));
        bookGroup.setEdition(resultSet.getString(Parameters.EDITION));
        bookGroup.setGenreId(resultSet.getInt(Parameters.GENRE));
        return bookGroup;
    }

    @Override
    public void unMap(PreparedStatement preparedStatement, BookGroup book) throws SQLException {
        preparedStatement.setString(1,book.getName());
        preparedStatement.setString(2,book.getAuthor());
        preparedStatement.setString(3,book.getEdition());
        preparedStatement.setDate(4,new java.sql.Date(book.getPublicationDate().getTime()));
        preparedStatement.setInt(5,book.getGenreId());

    }
}
