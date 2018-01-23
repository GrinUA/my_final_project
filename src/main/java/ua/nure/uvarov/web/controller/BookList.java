package ua.nure.uvarov.web.controller;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.Book;
import ua.nure.uvarov.services.BookService;
import ua.nure.uvarov.util.ValidateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@WebServlet("/book-list.do")
public class BookList extends HttpServlet {
    private BookService bookService;
    private ValidateUtil validateUtil;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestHandler(req, resp);
        URL url = new URL(req.getRequestURL().toString());

        String realPath = url.getPath();
        System.out.println(realPath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
       bookService = (BookService) config.getServletContext().getAttribute(Parameters.BOOK_SERVICE);
        validateUtil = new ValidateUtil();

    }

    private void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      /*  List<String> categories = bookService.getGenres();
        List<String> manufacturers = bookService.getGroups();
        List<Book> productList = bookService.getBookByParameters(filterParams, amount, offset);
        int count = productLayer.getCountByCondition(filterParams);

        request.setAttribute(Parameters.PRODUCT_CATEGORIES, categories);
        request.setAttribute(Parameters.PRODUCT_MANUFACTURERS, manufacturers);
        request.setAttribute(Parameters.PRODUCT_LIST, productList);
        request.setAttribute(Parameters.PRODUCT_COUNT, count);
        request.setAttribute(Parameters.AMOUNT, amount);
        request.setAttribute(Parameters.OFFSET, offset);
        request.setAttribute(Parameters.FILTER_PARAMETERS, filterParams);
        request.setAttribute(Parameters.QUERY_URL, url);
        request.getRequestDispatcher("shop.jsp").forward(request, response);

    }

    private FilterParams getFilterParam(HttpServletRequest request) {
        DozerBeanMapper mapper = (DozerBeanMapper) request.getServletContext().getAttribute("mapper");
        Map<String, String[]> paramMap = request.getParameterMap();

        return mapper.map(paramMap, FilterParams.class);
    }

    private void correctFilterBean(FilterParams filterParams) {
        if (!validateUtil.validateByRegex(filterParams.getName(), CommonConstants.PRODUCT_NAME_PATTERN)){
            filterParams.setName(Parameters.DEFAULT_PARAMETER_VALUE);
        }
        if(!validateUtil.validateByRegex(filterParams.getMinPrice(),CommonConstants.PRODUCT_PRICE_PATTERN)){
            filterParams.setMinPrice(Parameters.DEFAULT_PARAMETER_VALUE);
        }
        if(!validateUtil.validateByRegex(filterParams.getMaxPrice(),CommonConstants.PRODUCT_PRICE_PATTERN)){
            filterParams.setMaxPrice(Parameters.DEFAULT_PARAMETER_VALUE);
        }*/
    }


}
