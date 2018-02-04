package ua.nure.uvarov.dao;

import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;

import java.util.List;

public interface BookGroupDao extends GenericDao<BookGroup> {
    List<BookGroup> getAll();
    BookGroup getById(String id);
    BookGroup getByBook(int id);
    BookGroup getBookGroupByName(String name);
    BookGroup getBookGroupByAuthor(String author);
    List<BookGroup> getBookGroupByGenre(int id);
    boolean updateBook(BookGroup bookGroup);

    List<String> getGenres();
    Genre getGenreById(int id);
    Genre getGenreByName(String name);

    boolean isExist(String id);
    int getBookCountByState(boolean available, String groupId);
}
