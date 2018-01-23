package ua.nure.uvarov.dao.mysql;

import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.mapper.BookGroupRowMapper;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.exceptions.DataBaseException;
import ua.nure.uvarov.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookGroupDaoImpl implements BookGroupDao {
    @Override
    public BookGroup getById(int id) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUP_BY_ID)) {
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();

            BookGroup bookGroup = new BookGroupRowMapper().mapRow(resultSet);
            return bookGroup;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public int create(BookGroup entity) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.CREATE_BOOK_GROUP, Statement.RETURN_GENERATED_KEYS)) {
            new BookGroupRowMapper().unMap(st,entity);
            st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            resultSet.next();
            entity.setId(resultSet.getInt(1));
            return entity.getId();
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

                BookGroup bookGroup = new BookGroup();
                bookGroup.setId(resultSet.getInt(Parameters.ID));


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
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_ALL_GENRES)){
            list = new ArrayList<>();
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast())
                resultSet.next();
            list.add(new BookGroupRowMapper().mapRow(resultSet));
        } catch (SQLException e){
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public List<String> getGenres() {
        List<String> list;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_ALL_GENRES)){
            list = new ArrayList<>();
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast())
                resultSet.next();
            list.add(resultSet.getString(1));
        } catch (SQLException e){
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public BookGroup getBookGroupByName(String name) {
        BookGroup bookGroup;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUPS_BY_NAME)){
            BookGroupRowMapper bookGroupRowMapper = new BookGroupRowMapper();
            st.setString(1, name);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            bookGroup = bookGroupRowMapper.mapRow(resultSet);
        } catch (SQLException e){
            throw new DataBaseException(e);
        }
        return bookGroup;
    }

    @Override
    public BookGroup getBookGroupByAuthor(String author) {
        BookGroup bookGroup;
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_GROUPS_BY_AUTHOR)){
            BookGroupRowMapper bookGroupRowMapper = new BookGroupRowMapper();
            st.setString(1, author);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            bookGroup = bookGroupRowMapper.mapRow(resultSet);
        } catch (SQLException e){
            throw new DataBaseException(e);
        }
        return bookGroup;
    }
}
