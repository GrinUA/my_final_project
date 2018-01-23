package ua.nure.uvarov.services;

import ua.nure.uvarov.entity.Book;

import java.util.List;

public interface BookService {
    List<String> getGenres();
    List<String> getGroups();
    Book getById(int id);
    Book getBookByParameters(BookParams bookParams);
}
