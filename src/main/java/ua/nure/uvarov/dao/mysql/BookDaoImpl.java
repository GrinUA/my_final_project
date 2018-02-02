package ua.nure.uvarov.dao.mysql;

import ua.nure.uvarov.constants.MySQL;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.mapper.BookGroupRowMapper;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.exceptions.DataBaseException;
import ua.nure.uvarov.transaction.ThreadLockHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public void addBookToGroup(Book book) {

    }

    @Override
    public List<Book> getBooksByGroup(int id) {
        List<Book> list;

        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.BOOK_BY_GROUP)) {
            list = new ArrayList<>();
            st.setInt(1, id);
            st.executeQuery();
            ResultSet resultSet = st.getResultSet();
            while (!resultSet.isLast())
                resultSet.next();
            Book book = new Book();
            book.setId(resultSet.getInt(Parameters.ID));
            book.setGroupId(resultSet.getInt(Parameters.GROUP_ID));
            book.setAvailable(resultSet.getInt(Parameters.AVAILABLE)==1);

            list.add(book);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public boolean isAvailableById(int id) {
           Connection connection = ThreadLockHandler.getConnection();
            try (PreparedStatement st = connection.prepareStatement(MySQL.BOOK_AVAILABLE)) {
                st.setInt(1, id);
                ResultSet resultSet = st.executeQuery();
                resultSet.next();
                return (resultSet.getInt(Parameters.AVAILABLE))==1;
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
    }



    public Book availableBookFromGroupId(String id) {
        Connection connection = ThreadLockHandler.getConnection();
        try (PreparedStatement st = connection.prepareStatement(MySQL.FIND_BOOK_FROM_GROUP)) {
            st.setString(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            Book book = new Book();
            book.setId(resultSet.getInt(Parameters.ID));
            book.setGroupId(resultSet.getInt(Parameters.GROUP_ID));
            book.setAvailable(resultSet.getInt(Parameters.AVAILABLE)==1);
            return book;
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }


    public Book getById(int id) {
        return null;
    }

    public int create(Book book) {
        {
            Connection connection = ThreadLockHandler.getConnection();
            try (PreparedStatement st = connection.prepareStatement(MySQL.CREATE_BOOK, Statement.RETURN_GENERATED_KEYS)) {
                st.setInt(1, book.getGroupId());
                st.executeUpdate();
                ResultSet resultSet = st.getGeneratedKeys();
                resultSet.next();
                book.setId(resultSet.getInt(1));
                return book.getId();
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }
    }


    public boolean updateStatus (boolean available, int id) {
        {
            Connection connection = ThreadLockHandler.getConnection();
            try (PreparedStatement st = connection.prepareStatement(MySQL.UPDATE_BOOK_STATUS)) {
                st.setInt(1, available?1:0);
                st.setInt(2, id);
                st.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }
        }
    }

    public void update(Book entity) {

    }

    public void delete(Book entity) {

    }
}
