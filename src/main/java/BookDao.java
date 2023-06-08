import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Connection connection;

    public BookDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/book_info?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
            String username = "root";
            String password = "111111";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加图书
     */
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO book(name, author, category, publisher, time, price) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, book.getName());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getCategory());
        statement.setString(4, book.getPublisher());
        statement.setDate(5, book.getTime());
        statement.setDouble(6, book.getPrice());
        statement.executeUpdate();
    }

    /**
     * 根据图书ID删除图书
     */
    public void deleteBookById(int id) throws SQLException {
        String sql = "DELETE FROM book WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    /**
     * 修改图书信息
     */
    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET name = ?, author = ?, category = ?, publisher = ?, time = ?, price = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getName());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getCategory());
        statement.setString(4, book.getPublisher());
        statement.setDate(5, book.getTime()); // 使用java.sql.Date对象
        statement.setDouble(6, book.getPrice());
        statement.setInt(7, book.getId());
        statement.executeUpdate();
    }

    /**
     * 根据图书ID查询图书
     */
    public Book queryBookById(int id) throws SQLException {
        String sql = "SELECT * FROM book WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setCategory(resultSet.getString("category"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setTime(resultSet.getDate("time"));
            book.setPrice(resultSet.getDouble("price"));
            return book;
        } else {
            return null;
        }
    }

    /**
     * 根据图书名称查询图书
     */
    public List<Book> queryBookByName(String name) throws SQLException {
        String sql = "SELECT * FROM book WHERE name LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setCategory(resultSet.getString("category"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setTime(resultSet.getDate("time")); // 使用java.sql.Date对象
            book.setPrice(resultSet.getDouble("price"));
            books.add(book);
        }
        return books;
    }

    /**
     * 根据图书作者查询图书
     */
    public List<Book> queryBookByAuthor(String author) throws SQLException {
        String sql = "SELECT * FROM book WHERE author LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + author + "%");
        ResultSet resultSet = statement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setCategory(resultSet.getString("category"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setTime(resultSet.getDate("time"));
            book.setPrice(resultSet.getDouble("price"));
            books.add(book);
        }
        return books;
    }

    /**
     * 根据图书分类查询图书
     */
    public List<Book> queryBookByCategory(String category) throws SQLException {
        String sql = "SELECT * FROM book WHERE category LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + category + "%");
        ResultSet resultSet = statement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setCategory(resultSet.getString("category"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setTime(resultSet.getDate("time"));
            book.setPrice(resultSet.getDouble("price"));
            books.add(book);
        }
        return books;
    }

    /**
     * 查询所有图书
     */
    public List<Book> queryAllBooks() throws SQLException {
        String sql = "SELECT * FROM book";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setCategory(resultSet.getString("category"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setTime(resultSet.getDate("time"));
            book.setPrice(resultSet.getDouble("price"));
            books.add(book);
        }
        return books;
    }
}