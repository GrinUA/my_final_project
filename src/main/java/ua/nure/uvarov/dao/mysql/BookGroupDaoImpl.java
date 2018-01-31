package ua.nure.uvarov.dao.mysql;

import javafx.util.Pair;
import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.mapper.BookGroupRowMapper;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;
import ua.nure.uvarov.exceptions.DataBaseException;
import ua.nure.uvarov.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BookGroupDaoImpl implements BookGroupDao {

    private Function<Integer, Genre> genreByIdFunction = genreId -> getGenreById(genreId);


    @Override
    public Genre getGenreById(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_GENRE_BY_ID)) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();

            Genre genre = new Genre();
            genre.setId(resultSet.getInt(Parameters.ID));
            genre.setName(resultSet.getString(Parameters.NAME));

            return genre;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public BookGroup getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int create(BookGroup entity) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.CREATE_BOOK_GROUP)) {
            new BookGroupRowMapper().unMap(st, entity);
            st.executeUpdate();
            return 0;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public void update(BookGroup entity) {

    }

    @Override
    public void delete(BookGroup entity) {

    }

    @Override
    public List<BookGroup> getAll() {
        List<BookGroup> list;

        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_ALL_BOOK_GROUP)) {
            list = new ArrayList<>();
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast()) {
                resultSet.next();
                BookGroup bookGroup = new BookGroupRowMapper(genreByIdFunction).mapRow(resultSet);
                list.add(bookGroup);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public List<BookGroup> getBookGroupByGenre(int id) {
        List<BookGroup> list;

        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUPS_GENRE)) {
            list = new ArrayList<>();
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast())
                resultSet.next();
            BookGroup bookGroup = new BookGroupRowMapper(genreByIdFunction).mapRow(resultSet);
            list.add(bookGroup);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public List<String> getGenres() {
        List<String> list;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_ALL_GENRES)) {
            list = new ArrayList<>();
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast())
                resultSet.next();
            list.add(resultSet.getString(1));
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public BookGroup getBookGroupByName(String name) {
        BookGroup bookGroup;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUPS_BY_NAME)) {
            BookGroupRowMapper bookGroupRowMapper = new BookGroupRowMapper(genreByIdFunction);
            st.setString(1, name);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            bookGroup = bookGroupRowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return bookGroup;
    }

    @Override
    public BookGroup getBookGroupByAuthor(String author) {
        BookGroup bookGroup;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUPS_BY_AUTHOR)) {
            BookGroupRowMapper bookGroupRowMapper = new BookGroupRowMapper(genreByIdFunction);
            st.setString(1, author);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            bookGroup = bookGroupRowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return bookGroup;
    }

    @Override
    public int getBookCountByState(boolean available, String groupId) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.COUNT_BOOKS_BY_STATE_AVAILABLE)) {
            st.setBoolean(1, available);
            st.setString(2,groupId);
            // for MySql: st.setInt(1, available? 1 : 0);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            return resultSet.getInt(Parameters.COUNT);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public BookGroup getById(String id) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUP_BY_ID)) {
            st.setString(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();

            BookGroup bookGroup = new BookGroupRowMapper(genreByIdFunction).mapRow(resultSet);
            return bookGroup;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }
}
