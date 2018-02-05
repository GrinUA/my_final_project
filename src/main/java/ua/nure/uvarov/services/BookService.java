package ua.nure.uvarov.services;

import ua.nure.uvarov.bean.FilterParams;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookService {

    List<BookGroup> getGroups();
    Book getById(int id);
    BookGroup getBookByParameters(BookParams bookParams);

    int createBookToGroup(int count, String groupId);
   String createBookGroup(BookGroup bookGroup);
    BookGroup getBookGroup(String guid);
    List<BookGroup> getBookGroups();
    List<BookGroup> getBookGroupsWithPenalty();
    boolean updateBookGroup(BookGroup bookGroup);
    BookGroup getBook (HttpServletRequest request);
    boolean updateBookStatus(int id, boolean status);
    List<Genre> getGenres();
    List<BookGroup> getBookGroup(FilterParams filterParams);


}
