import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookDataProcessor {
    private BookService bookService;

    public BookDataProcessor(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 备份图书信息
     */
    public void backupBooks() {
        try {
            List<Book> books = bookService.queryAllBooks();
            if (books.isEmpty()) {
                System.out.println("没有可备份的图书信息！");
                return;
            }

            // 创建备份文件
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String backupFileName = "book_backup_" + dateFormat.format(new Date()) + ".txt";
            File backupFile = new File(backupFileName);
            FileWriter fileWriter = new FileWriter(backupFile);

            // 将图书信息写入备份文件
            for (Book book : books) {
                String bookInfo = String.format("%s,%s,%s,%s,%s,%s,%.2f",
                        book.getName(), book.getIsbn(), book.getAuthor(), book.getCategory(),
                        book.getPublisher(), book.getTime(), book.getPrice());
                fileWriter.write(bookInfo + System.lineSeparator());
            }

            fileWriter.close();
            System.out.println("图书信息备份成功，备份文件名为：" + backupFileName);
        } catch (IOException | SQLException e) {
            System.out.println("备份图书信息失败，错误信息为：" + e.getMessage());
        }
    }

    /**
     * 导入图书信息
     */
    public void importBooks() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入要导入的图书信息文件名：");
            String fileName = scanner.nextLine();

            File importFile = new File(fileName);
            if (!importFile.exists()) {
                System.out.println("指定的文件不存在！");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(importFile));
            String line;
            List<Book> books = new ArrayList<>();

            // 逐行读取文件内容，并解析为Book对象
            while ((line = reader.readLine()) != null) {
                String[] bookInfo = line.split(",");
                if (bookInfo.length == 7) {
                    String name = bookInfo[0];
                    String ISBN = bookInfo[1];
                    String author = bookInfo[2];
                    String category = bookInfo[3];
                    String publisher = bookInfo[4];
                    String time = bookInfo[5];
                    double price = Double.parseDouble(bookInfo[6]);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(time);
                    Book book = new Book(name, ISBN, author, category, publisher, date, price);
                    books.add(book);
                }
            }

            reader.close();

            // 导入图书信息
            int importedCount = 0;
            try {
                importedCount = bookService.importBooks(books);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("成功导入 " + importedCount + " 本图书信息。");
        } catch (IOException | ParseException e) {
            System.out.println("导入图书信息失败，错误信息为：" + e.getMessage());
        }
    }
}

