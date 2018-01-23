package ua.nure.uvarov.services;

import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.transaction.DBManager;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    private DBManager dbManager;

    public BookServiceImpl(BookDao bookDao, DBManager dbManager) {
        this.bookDao = bookDao;
        this.dbManager = dbManager;
    }
    @Override
    public List<String> getGenres() {
        return dbManager.execute(()->bookDao.getGenres());
    }

    @Override
    public List<String> getGroups() {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public Book getBookByParameters(BookParams bookParams) {
        return null;
    }
}
