package ua.nure.uvarov.services;

import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.transaction.DBManager;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    private DBManager dbManager;
    private BookGroupDao bookGroupDao;

    public BookServiceImpl(BookDao bookDao, BookGroupDao bookGroupDao, DBManager dbManager) {
        this.bookDao = bookDao;
        this.dbManager = dbManager;
        this.bookGroupDao = bookGroupDao;
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

   /* @Override
    public BookGroup getBookGroup(int id) {
        return dbManager.execute(() -> bookGroupDao.getById(id));
    }
*/
    @Override
    public int createBookGroup(BookGroup bookGroup) {
        return dbManager.execute(() -> bookGroupDao.create(bookGroup));
    }

   /* @Override
    public BookGroup getBookGroup(String guid) {
        return dbManager.execute(() -> bookGroupDao.getBookGroupByGuid(guid));
    }*/

    @Override
    public List<BookGroup> getBookGroups() {
        return dbManager.execute(()->bookGroupDao.getAll());
    }


    @Override
    public BookGroup getBookGroup(String guid) {
        return null;
    }
}
