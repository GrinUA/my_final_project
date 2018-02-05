package ua.nure.uvarov.constants;

public final class MySQL {
    public static final String CREATE_USER = "insert into users (email,password,firstName,lastName,blocked,role) values (?,?,?,?,?,?);";
    public static final String USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?";
    public static final String USERS_BLOCK_STATUS = "SELECT blocked FROM users WHERE email = ?";
    public static final String UPDATE_BLOCK_STATUS_BY_EMAIL = "UPDATE users SET blocked = ? WHERE email = ?";
    public static final String USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String FIND_ALL_USERS = "SELECT * FROM users";
    public static final String FIND_USERS_BY_PARAMETHER = "SELECT * FROM users";

    public static final String FIND_GENRE_BY_ID = "SELECT * FROM genres WHERE id = ?";
    public static final String FIND_GENRE_BY_NAME = "SELECT * FROM genres WHERE name = ?";

    public static final String CREATE_BOOK = "insert into books (id,groupId,available) values (DEFAULT,?,1);";
    public static final String FIND_BOOK_GROUPS_BY_NAME = "SELECT * FROM book_groups WHERE name = ?";
    public static final String FIND_BOOK_GROUPS_BY_AUTHOR = "SELECT * FROM book_groups WHERE author = ?";
    public static final String FIND_BOOK_GROUPS_GENRE = "SELECT * FROM book_groups WHERE genreId = ?";
    public static final String BOOK_BY_GROUP = "SELECT * FROM books WHERE id = ?";
    public static final String BOOK_AVAILABLE = "SELECT available FROM books WHERE id = ?";
    public static final String FIND_ALL_GENRES = "SELECT * FROM genres";
    public static final String UPDATE_BOOK_STATUS = "UPDATE books SET available = ? WHERE id = ?";

    public static final String GROUP_BY_BOOK = "SELECT * FROM book WHERE groupId = ?";
    public static final String UPDATE_BOOK_GROUP = "UPDATE book_groups SET name=?,author=?,edition=?,publicationdate=?,genreid=?,price=?,description=? WHERE id = ?";
    public static final String CREATE_BOOK_GROUP =
            "INSERT into book_groups (id,name,author,edition,publicationDate,description, price,genreId, image) values (?,?,?,?,?,?,?,?,?)";
    public static final String FIND_BOOK_GROUP_BY_ID = "SELECT * FROM book_groups WHERE id = ?";
    public static final String FIND_GROUP_BOOK_BY_BOOK = "SELECT  * from book_groups WHERE book_groups.id = (SELECT  groupId from books WHERE books.id = ?)";
    public static final String FIND_ALL_BOOK_GROUP = "SELECT DISTINCT *\n" +
            "FROM (SELECT\n" +
            "        bg.id,\n" +
            "        bg.name,\n" +
            "        bg.author,\n" +
            "        bg.description,\n" +
            "        bg.price,\n" +
            "        bg.genreid,\n" +
            "        bg.publicationdate,\n" +
            "        bg.image,\n" +
            "        bg.edition\n" +
            "      FROM book_groups AS bg RIGHT JOIN books as b ON bg.id = b.groupId) as mergeTable";
    public static final String COUNT_BOOKS_BY_STATE_AVAILABLE = "SELECT COUNT(id) as count FROM books where available = ? and groupId = ?";
    public static final String FIND_BOOK_FROM_GROUP = "SELECT * FROM books where available = 1 and groupId = ?";

    public static final String COUNT_ORDERS_FOR_BOOK_GROUP = "SELECT count(o.id) as count from books as b INNER JOIN  orders as o ON b.id = o.bookid where b.groupid = ? and status in ('WAITING','OPEN') ";


    public static final String CREATE_ORDER_BY_USER = "insert into orders (bookId,userId,date_expected,date_order, place, status,guId) values (?,?,?,?,?,?,?);";
    public static final String USER_ORDERS = "SELECT * FROM orders WHERE userId = ?";
    public static final String ALL_ORDERS = "SELECT * FROM orders";
    public static final String UPDATE_ORDER = "UPDATE orders SET date_borrow=?,date_expected=?,date_order=?,date_close=?,place=?,status=?,penalty=? WHERE id = ?";
    public static final String ORDER_BY_GUID = "SELECT * FROM orders WHERE guId = ?";


}
