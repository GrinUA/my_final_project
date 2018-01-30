package ua.nure.uvarov.handler;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.BookGroup;
import ua.nure.uvarov.entity.User;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCabinetHandler implements PersonalCabinetHandler {


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getParameter(Parameters.ACTIVE_TAB));
      if(req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_USERS) ||
              req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_DEFAULT)) {
          UserService  userService = (UserService) req.getServletContext().getAttribute(Parameters.USER_SERVICE);
          List<User> list = userService.getAllUsers();
          req.setAttribute(Parameters.ALL_USERS, list);
          req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_USERS);
      }
      if(req.getParameter(Parameters.ACTIVE_TAB).equals(Parameters.TAB_BOOKS)){
          BookService bookService = (BookService) req.getServletContext().getAttribute(Parameters.BOOK_SERVICE) ;
          List<BookGroup> list = bookService.getBookGroups();
          req.setAttribute(Parameters.ALL_BOOKS, list);
          req.setAttribute(Parameters.ACTIVE_TAB, Parameters.TAB_BOOKS);
      }
    }
}
