package ua.nure.uvarov.dao.mysql;

import ua.nure.uvarov.bean.FilterParams;
import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.mapper.BookGroupRowMapper;

import ua.nure.uvarov.dao.query.BookQueryBuilder;
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

    public Genre getGenreByName(String name) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_GENRE_BY_NAME)) {
            st.setString(1, name);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            if (resultSet.next()) {

                Genre genre = new Genre();
                genre.setId(resultSet.getInt(Parameters.ID));
                genre.setName(resultSet.getString(Parameters.NAME));

                return genre;
            } else return null;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }


    @Override
    public boolean isExist(String id) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUP_BY_ID)) {
            st.setString(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            return resultSet.next();
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
    public List<BookGroup> findByCondition(FilterParams filterParams) {
        List<BookGroup> list;
        BookQueryBuilder bookQueryBuilder = new BookQueryBuilder();
        bookQueryBuilder.setParams(filterParams);
        bookQueryBuilder.build();
        String query = bookQueryBuilder.toString();

        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            list = new ArrayList<>();
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (resultSet.next()) {
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
    public boolean updateBook(BookGroup bookGroup) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.UPDATE_BOOK_GROUP)) {
            st.setString(1, bookGroup.getName());
            st.setString(2, bookGroup.getAuthor());
            st.setString(3, bookGroup.getEdition());
            st.setDate(4, new java.sql.Date(bookGroup.getPublicationDate().getTime()));
            st.setInt(5, bookGroup.getGenre().getId());
            st.setDouble(6, bookGroup.getPrice());
            st.setString(7, bookGroup.getDescription());
            st.setString(8, bookGroup.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Genre> getGenres() {
        List<Genre> list;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_ALL_GENRES)) {
            list = new ArrayList<>();
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast()) {
                resultSet.next();
                Genre genre = new Genre();
                genre.setId(resultSet.getInt(1));
                genre.setName(resultSet.getString(2));
                list.add(genre);
            }
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
            //st.setInt(1, available ? 1 : 0);
            st.setBoolean(1, available);
            st.setString(2, groupId);
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
            return new BookGroupRowMapper(genreByIdFunction).mapRow(resultSet);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public BookGroup getByBook(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_GROUP_BOOK_BY_BOOK)) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            return new BookGroupRowMapper(genreByIdFunction).mapRow(resultSet);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }


}
