package ua.nure.uvarov.dao;

import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;

import java.util.List;


public interface BookDao extends GenericDao<Book> {

    List<Book> getBooksByGroup(int id);
    boolean isAvailableById(int id);
    void addBookToGroup(Book book);
    Book availableBookFromGroupId(String id);
    boolean updateStatus (boolean available, int id);

}
