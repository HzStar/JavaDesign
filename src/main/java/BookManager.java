import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

public class BookManager {
    static BookService bookService = new BookService();
    static BookDataProcessor dataProcessor = new BookDataProcessor(bookService);
    public BookManager() {
        bookService = new BookService();
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入图书名称：");
        String name = scanner.nextLine();
        System.out.println("请输入图书ISBN号：");
        String isbn = scanner.nextLine();
        System.out.println("请输入图书作者：");
        String author = scanner.nextLine();
        System.out.println("请输入图书分类：");
        String category = scanner.nextLine();
        System.out.println("请输入出版社名称：");
        String publisher = scanner.nextLine();
        System.out.println("请输入出版时间（格式为YYYY-MM-DD）：");
        String timeStr = scanner.nextLine();
        System.out.println("请输入图书价格：");
        double price = scanner.nextDouble();
        scanner.nextLine();

        try {
            Book book = new Book(name, isbn, author, category, publisher, Date.valueOf(timeStr), price);
            bookService.addBook(book);
            System.out.println("添加图书成功，图书编号为：" + book.getId());
        } catch (SQLException e) {
            System.out.println("添加图书失败，错误信息为：" + e.getMessage());
        }

    }

    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的图书编号：");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            bookService.deleteBookById(id);
            System.out.println("删除图书成功");
        } catch (SQLException e) {
            System.out.println("删除图书失败，错误信息为：" + e.getMessage());
        }
    }

    public void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要修改的图书编号：");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入图书名称：");
        String name = scanner.nextLine();
        System.out.println("请输入图书ISBN号：");
        String isbn = scanner.nextLine();
        System.out.println("请输入图书作者：");
        String author = scanner.nextLine();
        System.out.println("请输入图书分类：");
        String category = scanner.nextLine();
        System.out.println("请输入出版社名称：");
        String publisher = scanner.nextLine();
        System.out.println("请输入出版时间（格式为YYYY-MM-DD）：");
        String timeStr = scanner.nextLine();
        System.out.println("请输入图书价格：");
        double price = scanner.nextDouble();
        scanner.nextLine();

        try {
            Book book = new Book(name, isbn, author, category, publisher, Date.valueOf(timeStr), price);
            book.setId(id);
            bookService.updateBook(book);
            System.out.println("修改图书成功");
        } catch (SQLException e) {
            System.out.println("修改图书失败，错误信息为：" + e.getMessage());
        }
    }

    public void queryBookByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询的图书名称：");
        String name = scanner.nextLine();

        try {
            List<Book> books = bookService.queryBookByName(name);
            System.out.println("查询结果如下：");
            for (Book book : books) {
                System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getCategory() + "\t" + book.getPublisher() + "\t" + book.getTime() + "\t" + book.getPrice());
            }
        } catch (SQLException e) {
            System.out.println("查询图书失败，错误信息为：" + e.getMessage());
        }
    }

    public void queryBookByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询的图书作者：");
        String author = scanner.nextLine();

        try {
            List<Book> books = bookService.queryBookByAuthor(author);
            System.out.println("查询结果如下：");
            for (Book book : books) {
                System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getCategory() + "\t" + book.getPublisher() + "\t" + book.getTime() + "\t" + book.getPrice());
            }
        } catch (SQLException e) {
            System.out.println("查询图书失败，错误信息为：" + e.getMessage());
        }
    }

    public void queryBookByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询的图书分类：");
        String category = scanner.nextLine();
        try {
            List<Book> books = bookService.queryBookByCategory(category);
            System.out.println("查询结果如下：");
            for (Book book : books) {
                System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getCategory() + "\t" + book.getPublisher() + "\t" + book.getTime() + "\t" + book.getPrice());
            }
        } catch (SQLException e) {
            System.out.println("查询图书失败，错误信息为：" + e.getMessage());
        }
    }

    public void queryAllBooks() {
        try {
            List<Book> books = bookService.queryAllBooks();
            System.out.println("查询结果如下：");
            for (Book book : books) {
                System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getAuthor() + "\t" + book.getCategory() + "\t" + book.getPublisher() + "\t" + book.getTime() + "\t" + book.getPrice());
            }
        } catch (SQLException e) {
            System.out.println("查询所有图书失败，错误信息为：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎使用图书管理系统，请输入对应的数字选择操作：");
            System.out.println("1. 添加图书");
            System.out.println("2. 删除图书");
            System.out.println("3. 修改图书");
            System.out.println("4. 按名称查询图书");
            System.out.println("5. 按作者查询图书");
            System.out.println("6. 按分类查询图书");
            System.out.println("7. 查询所有图书");
            System.out.println("0. 退出系统");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookManager.addBook();
                    break;
                case 2:
                    bookManager.deleteBook();
                    break;
                case 3:
                    bookManager.updateBook();
                    break;
                case 4:
                    bookManager.queryBookByName();
                    break;
                case 5:
                    bookManager.queryBookByAuthor();
                    break;
                case 6:
                    bookManager.queryBookByCategory();
                    break;
                case 7:
                    bookManager.queryAllBooks();
                    break;
                case 8:
                    // 导入图书信息
                    dataProcessor.importBooks();
                    break;

                case 9:
                    // 备份图书信息
                    dataProcessor.backupBooks();
                    break;

                case 0:
                    System.out.println("感谢使用图书管理系统，再见！");
                    System.exit(0);
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }
}