import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDao bookDao;

    public BookService() {
        bookDao = new BookDao();
    }

    /**
     * 添加图书
     */
    public void addBook(Book book) throws SQLException {
        bookDao.addBook(book);
    }

    /**
     * 根据图书ID删除图书
     */
    public void deleteBookById(int id) throws SQLException {
        bookDao.deleteBookById(id);
    }

    /**
     * 修改图书信息
     */
    public void updateBook(Book book) throws SQLException {
        bookDao.updateBook(book);
    }

    /**
     * 根据图书ID查询图书
     */
    public Book queryBookById(int id) throws SQLException {
        return bookDao.queryBookById(id);
    }

    /**
     * 根据图书名称查询图书
     */
    public List<Book> queryBookByName(String name) throws SQLException {
        return bookDao.queryBookByName(name);
    }

    /**
     * 根据图书作者查询图书
     */
    public List<Book> queryBookByAuthor(String author) throws SQLException {
        return bookDao.queryBookByAuthor(author);
    }

    /**
     * 根据图书分类查询图书
     */
    public List<Book> queryBookByCategory(String category) throws SQLException {
        return bookDao.queryBookByCategory(category);
    }

    /**
     * 查询所有图书
     */
    public List<Book> queryAllBooks() throws SQLException {
        return bookDao.queryAllBooks();
    }
}