package ua.nure.uvarov.services;

import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;

import java.util.List;

public interface BookService {

    List<BookGroup> getGroups();
    Book getById(int id);
    BookGroup getBookByParameters(BookParams bookParams);

   // BookGroup getBookGroup(int id);
    int createBookGroup(BookGroup bookGroup);
    BookGroup getBookGroup(String guid);
    List<BookGroup> getBookGroups();


}
