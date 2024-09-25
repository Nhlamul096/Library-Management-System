/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.dao;

import java.sql.Date;
import java.util.List;
import za.co.material.Book;
import za.co.material.Category;

/**
 *
 * @author Lungh
 */
public interface BooksDAO {

    public Book getBook(String isbnumber);
    public Book getBook(int bookID);
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
    public boolean updateBookAuthor(String isbnumber, String authors);
    public boolean updateBookTitle(String isbnumber, String title);
    public boolean updateBookCategory(String isbnumber, int category);
    public boolean updateBookAvailability(int bookID, boolean isAvailable);
    public boolean updateBookRackNo(int bookId, String rackNo);
    public boolean updateBookPubDate(String isbnumber, Date pubDate);
    public boolean deleteBook(int bookId);
    public boolean updateCategory(Category category);
}
