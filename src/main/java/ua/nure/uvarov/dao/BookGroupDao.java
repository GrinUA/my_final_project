package ua.nure.uvarov.dao;

import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;

import java.util.List;

public interface BookGroupDao extends GenericDao<BookGroup> {
    List<BookGroup> getAll();
    BookGroup getBookGroupByName(String name);
    BookGroup getBookGroupByAuthor(String author);
    List<BookGroup> getBookGroupByGenre(int id);
    List<String> getGenres();
}
