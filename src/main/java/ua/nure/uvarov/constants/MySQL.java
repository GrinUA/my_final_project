package ua.nure.uvarov.constants;

public final class MySQL {
    public static final String CREATE_USER = "insert into users (email,password,firstName,lastName,blocked,role) values (?,?,?,?,?,?);";
    public static final String USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?";
    public static final String USERS_BLOCK_STATUS = "SELECT blocked FROM users WHERE email = ?";
    public static final String UPDATE_BLOCK_STATUS_BY_EMAIL = "UPDATE users SET blocked = ? WHERE email = ?";
    public static final String USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String FIND_ALL_USERS = "SELECT * FROM users";

    public static final String FIND_GENRE_BY_ID = "SELECT * FROM genres WHERE id = ?";

    public static final String CREATE_BOOK = "insert into books (id,groupid,unavailable) values (DEFAULT,?,DEFAULT);";
    public static final String FIND_BOOK_GROUPS_BY_NAME = "SELECT * FROM book_groups WHERE name = ?";
    public static final String FIND_BOOK_GROUPS_BY_AUTHOR = "SELECT * FROM book_groups WHERE author = ?";
    public static final String FIND_BOOK_GROUPS_GENRE = "SELECT * FROM book_groups WHERE genreId = ?";
    public static final String BOOK_BY_GROUP = "SELECT * FROM books WHERE id = ?";
   // public static final String BOOK_AVAILABLE_STATUS = "SELECT unavailable FROM books WHERE id = ?";
    public static final String FIND_ALL_GENRES = "SELECT * FROM genres";

    public static final String CREATE_BOOK_GROUP =
            "INSERT into book_groups (id,name,author,edition,publicationDate,description, price,genreId, image) values (?,?,?,?,?,?,?,?,?)";
    public static final String FIND_BOOK_GROUP_BY_ID = "SELECT * FROM book_groups WHERE id = ?";
    public static final String FIND_ALL_BOOK_GROUP = "SELECT * FROM book_groups";
    public static final String COUNT_BOOKS_BY_STATE_UNAVAILABLE = "SELECT COUNT(id) as count FROM books where unavailable = ?";


    public static final String COUNT_ORDERS_FOR_BOOK_GROUP = "SELECT count(o.id) as count from books as b INNER JOIN  orders o ON b.id = o.bookid where b.groupid = ?";





}
