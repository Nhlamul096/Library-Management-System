
package za.co.material.service;

import java.sql.Date;
import java.util.List;
import za.co.material.Book;
import za.co.material.Category;
import za.co.process.ProcessInt;


public interface BooksService {
    public Book getBook(int bookId);
    public Book getBook(String isbnumber);
    public List<String> getAuthor(String isbnumber);
    public String getTitle(String isbnumber);
    public String getCategory(String isbnumber);
    public Date getPubDate(String isbnumber);
    public boolean getIsAvailable(String isbnumber);
    public String getRackno(int bookId);
    
    public List<Book> getBookByAuthor(String authors);
    public List<Book> getBookByTitle(String title);
    public List<Book> getBookByCategory(int category);
    public List<Book> getBookByPubDate(Date pubDate);
    
    public boolean addBook(Book book, int catId);
    public boolean updateBook(Book book);
    public boolean updateBookAuthor(String isbnumber,String authors);
    public boolean updateBookTitle(String isbnumber,String title);
    public boolean updateBookCategory(String isbnumber,int category);
    public boolean updateBookAvailability(int bookid, boolean isAvailable);
    public boolean updateBookRackNo(int bookid,String rackNo);
    public boolean updateBookPubDate(String isbnumber,Date pubDate);
    public boolean deleteBook(int bookId);
    public boolean updateCategory(Category category);
    
}
