package ua.nure.uvarov.services;

import sun.net.www.content.text.Generic;
import ua.nure.uvarov.bean.FilterParams;
import ua.nure.uvarov.bean.rowMapper.BeanRowMapper;
import ua.nure.uvarov.bean.rowMapper.BookEditBeanRowMapper;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.dao.BookDao;
import ua.nure.uvarov.dao.BookGroupDao;
import ua.nure.uvarov.dao.mapper.BookGroupRowMapper;
import ua.nure.uvarov.dao.mapper.EntityRowMapper;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.Genre;
import ua.nure.uvarov.exceptions.AppException;
import ua.nure.uvarov.exceptions.NotFoundException;
import ua.nure.uvarov.transaction.DBManager;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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

    @Override
    public int createBookToGroup(int count, String groupId) {
        return dbManager.execute(() -> {
            for (int i = 0; i < count; i++) {
                Book book = new Book();
                book.setGroupId(groupId);
                bookDao.create(book);
            }
                return count;});
    }

    /* @Override
     public BookGroup getBookGroup(int id) {
         return dbManager.execute(() -> bookGroupDao.getById(id));
     }
 */
    @Override
    public String createBookGroup(BookGroup bookGroup) {
        return dbManager.execute(() -> bookGroupDao.createBookGroup(bookGroup));
    }


    @Override
    public List<BookGroup> getBookGroups() {
        return dbManager.execute(() -> bookGroupDao.getAll());
    }

    @Override
    public List<BookGroup> getBookGroupsWithPenalty() {
        return dbManager.execute(() -> bookGroupDao.getAll());
    }

    @Override
    public boolean updateBookGroup(BookGroup bookGroup) {

        return dbManager.execute(() ->

                bookGroupDao.updateBook(bookGroup));
    }




    public BookGroup getBook(HttpServletRequest request) {
        BeanRowMapper bookGroupRowMapper = new BookEditBeanRowMapper();
        Genre genre = getGenreByName(request.getParameter(Parameters.GENRE_NAME));
        if(genre != null) {
            request.setAttribute("genre", genre);
            return (BookGroup) bookGroupRowMapper.mapRow(request);
        }
        else return null;
    }

    @Override
    public boolean updateBookStatus(int id, boolean status) {
        return dbManager.execute(() -> bookDao.updateStatus(status, id));
    }

    public Genre getGenreByName(String name){
        return dbManager.execute(() -> bookGroupDao.getGenreByName(name));
    }


    @Override
    public BookGroup getBookGroup(String guid) {
        return dbManager.execute(() -> {
            if (bookGroupDao.isExist(guid)) {
                return bookGroupDao.getById(guid);
            } else return null;
        });
    }

    @Override
    public List<Genre> getGenres() {
        return dbManager.execute(() -> bookGroupDao.getGenres());
    }

    @Override
    public List<BookGroup> getBookGroup(FilterParams filterParams) {
        return dbManager.execute(() -> bookGroupDao.findByCondition(filterParams));
    }

}

