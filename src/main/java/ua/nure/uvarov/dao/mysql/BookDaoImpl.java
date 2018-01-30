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
            book.setAvailable(resultSet.getBoolean(Parameters.AVAILABLE)/*==1*/);

            list.add(book);
        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
        return list;
    }

    @Override
    public boolean isUnavailable(int id) {
           /* Connection connection = ThreadLockHandler.getConnection();
            try (PreparedStatement st = connection.prepareStatement(MySQL.BOOK_AVAILABLE_STATUS)) {
                st.setInt(1, id);
                ResultSet resultSet = st.executeQuery();
                resultSet.next();
                return (resultSet.getInt(Parameters.UNAVAILABLE))==1;
            } catch (SQLException e) {
                throw new DataBaseException(e);
            }*/
        return true;
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

    public void update(Book entity) {

    }

    public void delete(Book entity) {

    }
}
