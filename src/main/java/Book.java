import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Book {
    private int id;
    private String name;
    private String isbn;
    private String author;
    private String category;
    private String publisher;
    private java.sql.Date time;
    private double price;

    public Book() {}

    public Book(String name, String isbn, String author, String category, String publisher, Date time, double price) {
        setName(name);
        setIsbn(isbn);
        setAuthor(author);
        setCategory(category);
        setPublisher(publisher);
        setTime((java.sql.Date) time);
        setPrice(price);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public java.sql.Date getTime() {
        return time;
    }

    public void setTime(java.sql.Date time) {
        this.time = time;
    }

    public void setTime(String timeStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date time = format.parse(timeStr);
        this.time = new java.sql.Date(time.getTime());
    }




    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}