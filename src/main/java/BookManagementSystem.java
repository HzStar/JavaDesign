import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookManagementSystem {
    public static void main(String[] args) throws ParseException {
        BookService bookService = new BookService();

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
                    System.out.println("请输入图书信息：");
                    System.out.print("名称：");
                    String name = scanner.nextLine();
                    System.out.print("ISBN：");
                    String ISBN = scanner.nextLine();
                    System.out.print("作者：");
                    String author = scanner.nextLine();
                    System.out.print("分类：");
                    String category = scanner.nextLine();
                    System.out.print("出版社：");
                    String publisher = scanner.nextLine();
                    System.out.print("出版时间：");
                    String time = scanner.nextLine();
                    System.out.print("价格：");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(time);
                    Book book = new Book(name, ISBN, author, category, publisher, date, price);

                    try {
                        bookService.addBook(book);
                        System.out.println("添加图书成功！");
                    } catch (SQLException e) {
                        System.out.println("添加图书失败，错误信息为：" + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("请输入要删除的图书的ID：");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        bookService.deleteBookById(id);
                        System.out.println("删除图书成功！");
                    } catch (SQLException e) {
                        System.out.println("删除图书失败，错误信息为：" + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("请输入要修改的图书的ID：");
                    int modifyId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Book modifyBook = bookService.queryBookById(modifyId);
                        if (modifyBook == null) {
                            System.out.println("找不到要修改的图书！");
                        } else {
                            System.out.println("请输入新的图书信息：");
                            System.out.print("名称（原名称为" + modifyBook.getName() + "）：");
                            String newName = scanner.nextLine();
                            if (!newName.equals("")) {
                                modifyBook.setName(newName);
                            }
                            System.out.print("作者（原作者为" + modifyBook.getAuthor() + "）：");
                            String newAuthor = scanner.nextLine();
                            if (!newAuthor.equals("")) {
                                modifyBook.setAuthor(newAuthor);
                            }
                            System.out.print("分类（原分类为" + modifyBook.getCategory() + "）：");
                            String newCategory = scanner.nextLine();
                            if (!newCategory.equals("")) {
                                modifyBook.setCategory(newCategory);
                            }
                            System.out.print("出版社（原出版社为" + modifyBook.getPublisher() + "）：");
                            String newPublisher = scanner.nextLine();
                            if (!newPublisher.equals("")) {
                                modifyBook.setPublisher(newPublisher);
                            }
                            System.out.print("出版时间（原出版时间为" + modifyBook.getTime() + "）：");
                            String newTime = scanner.nextLine();
                            if (!newTime.equals("")) {
                                modifyBook.setTime(newTime);
                            }
                            System.out.print("价格（原价格为" + modifyBook.getPrice() + "）：");
                            String newPriceStr = scanner.nextLine();
                            if (!newPriceStr.equals("")) {
                                double newPrice = Double.parseDouble(newPriceStr);
                                modifyBook.setPrice(newPrice);
                            }
                            bookService.updateBook(modifyBook);
                            System.out.println("修改图书成功！");
                        }
                    } catch (SQLException e) {
                        System.out.println("修改图书失败，错误信息为：" + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("请输入要查询的图书名称：");
                    String queryName = scanner.nextLine();
                    try {
                        List<Book> books = bookService.queryBookByName(queryName);
                        if(books.size() == 0)
                        {
                            System.out.println("找不到图书！");
                        } else{
                            System.out.println("查询结果如下：");
                            for (Book queryBook : books) {
                                System.out.println(queryBook.getId() + "\t" + queryBook.getName() + "\t" + queryBook.getAuthor() + "\t" + queryBook.getCategory() + "\t" + queryBook.getPublisher() + "\t" + queryBook.getTime() + "\t" + queryBook.getPrice());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("查询图书失败，错误信息为：" + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("请输入要查询的图书作者：");
                    String queryAuthor = scanner.nextLine();
                    try {
                        List<Book> books = bookService.queryBookByAuthor(queryAuthor);
                        if (books.size() == 0) {
                            System.out.println("找不到图书！");
                        } else {
                            System.out.println("查询结果如下：");
                            for (Book queryBook : books) {
                                System.out.println(queryBook.getId() + "\t" + queryBook.getName() + "\t" + queryBook.getAuthor() + "\t" + queryBook.getCategory() + "\t" + queryBook.getPublisher() + "\t" + queryBook.getTime() + "\t" + queryBook.getPrice());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("查询图书失败，错误信息为：" + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.print("请输入要查询的图书分类：");
                    String queryCategory = scanner.nextLine();
                    try {
                        List<Book> books = bookService.queryBookByCategory(queryCategory);
                        if (books.size() == 0) {
                            System.out.println("找不到图书！");
                        } else {
                            System.out.println("查询结果如下：");
                            for (Book queryBook : books) {
                                System.out.println(queryBook.getId() + "\t" + queryBook.getName() + "\t" + queryBook.getAuthor() + "\t" + queryBook.getCategory() + "\t" + queryBook.getPublisher() + "\t" + queryBook.getTime() + "\t" + queryBook.getPrice());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("查询图书失败，错误信息为：" + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        List<Book> books = bookService.queryAllBooks();
                        if (books.size() == 0) {
                            System.out.println("暂无图书！");
                        } else {
                            System.out.println("查询结果如下：");
                            System.out.println("ID\t\t图书名称\t\t作者\t\t类别\t\t出版社\t\t出版时间\t\t价格");
                            for (Book queryBook : books) {
                                System.out.print(queryBook.getId() + "\t\t" + queryBook.getName() + "\t\t"
                                        + queryBook.getAuthor() + "\t\t" + queryBook.getCategory() + "\t" +
                                        queryBook.getPublisher() + "\t\t" + queryBook.getTime() + "\t\t" + queryBook.getPrice());
                                System.out.print("\n");
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("查询所有图书失败，错误信息为：" + e.getMessage());
                    }
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