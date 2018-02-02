package ua.nure.uvarov.services;

import ua.nure.uvarov.bean.rowMapper.BeanRowMapper;
import ua.nure.uvarov.bean.rowMapper.BookEditBeanRowMapper;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.mapper.BookGroupRowMapper;
import ua.nure.uvarov.dao.mapper.EntityRowMapper;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.transaction.DBManager;

import javax.servlet.http.HttpServletRequest;
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
    public List<BookGroup> getGroups() {
        return dbManager.execute(() -> bookGroupDao.getAll());
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public BookGroup getBookByParameters(BookParams bookParams) {
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
        return dbManager.execute(() -> bookGroupDao.getAll());
    }

    @Override
    public boolean updateBookGroup(BookGroup bookGroup) {

       return dbManager.execute(() ->
               bookGroupDao.updateBook(bookGroup));
    }

    public BookGroup getBook (HttpServletRequest request) {
        BeanRowMapper bookGroupRowMapper = new BookEditBeanRowMapper();
        return (BookGroup)bookGroupRowMapper.mapRow(request);
    }


    @Override
    public BookGroup getBookGroup(String guid) {
            return dbManager.execute(() ->{
                if (bookGroupDao.isExist(guid)){
                   return bookGroupDao.getById(guid);
                }
            else return null;});
        }

    }

