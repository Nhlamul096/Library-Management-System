package za.co.material.service.impl;

import java.sql.Date;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Book;
import za.co.material.Category;
import za.co.material.dao.BooksDAO;
import za.co.material.dao.impl.BooksDAOImpl;
import za.co.material.service.BooksService;

/**
 *
 * @author TRAIN 76
 */
public class BooksServiceImpl implements BooksService {

    private BooksDAO booksdao;

    public BooksServiceImpl(BasicDataSource dbm) {
        booksdao = new BooksDAOImpl(dbm);
    }
    
    @Override
    public boolean addBook(Book book, int catId) {
        if (book != null && catId > 0) {
            return booksdao.addBook(book, catId);
        }
        return false;
    }

    public Book getBook(String isbn) {
        return booksdao.getBook(isbn);
    }

    @Override
    public boolean updateBook(Book book) {
        if (book == null) {
            return false;
        }
        return booksdao.updateBook(book);
    }

    @Override
    public boolean deleteBook(int bookId) {
        if (bookId < 1) {
            return false;
        }
        return booksdao.deleteBook(bookId);
    }

    @Override
    public boolean updateBookAuthor(String isbnumber, String authors) {
        if (isbnumber != null && authors != null && !authors.isEmpty()) {
            return booksdao.updateBookAuthor(isbnumber, authors);
        }
        return false;
    }

    @Override
    public boolean updateBookTitle(String isbnumber, String title) {
        if (isbnumber != null && !isbnumber.isEmpty() && title != null && !title.isEmpty()) {
            return booksdao.updateBookAuthor(isbnumber, title);
        }
        return false;
    }

    @Override
    public boolean updateBookCategory(String isbnumber, int category) {
        if (isbnumber != null && !isbnumber.isEmpty() && category>0) {
            return booksdao.updateBookCategory(isbnumber, category);
        }
        return false;
    }

    @Override
    public boolean updateBookAvailability(int bookid, boolean isAvailable) {
        if (bookid > 0 && isAvailable == true) {
            return booksdao.updateBookAvailability(bookid, isAvailable);
        }
        return false;
    }

    @Override
    public boolean updateBookRackNo(int bookid, String rackNo) {
        if (bookid > 0 && rackNo != null && !rackNo.isEmpty()) {
            return booksdao.updateBookRackNo(bookid, rackNo);
        }
        return false;
    }

    @Override
    public boolean updateBookPubDate(String isbnumber, Date pubDate) {
        if (!isbnumber.isEmpty() && isbnumber != null && pubDate != null) {
            return booksdao.updateBookPubDate(isbnumber, pubDate);
        }
        return false;
    }

    @Override
    public List<Book> getBookByAuthor(String authors) {
        if (!authors.isEmpty() && authors != null) {
            return booksdao.getBookByAuthor(authors);
        }
        return null;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        if (!title.isEmpty() && title != null) {
            return booksdao.getBookByTitle(title);
        }
        return null;
    }

    @Override
    public List<Book> getBookByCategory(int category) {
        if (category > 0) {
            return booksdao.getBookByCategory(category);
        }
        return null;
    }

    @Override
    public List<Book> getBookByPubDate(Date pubDate) {
        if (pubDate != null) {
            return booksdao.getBookByPubDate(pubDate);
        }
        return null;
    }

    @Override
    public Book getBook(int bookid) {
        if (bookid > 0) {
            return booksdao.getBook(bookid);
        }
        return null;
    }

    @Override
    public List<String> getAuthor(String isbnumber) {
        if (isbnumber != null && !isbnumber.isEmpty()) {
            return booksdao.getAuthor(isbnumber);
        }
        return null;
    }

    @Override
    public String getTitle(String isbnumber) {
        if (isbnumber != null && !isbnumber.isEmpty()) {
            return booksdao.getTitle(isbnumber);
        }
        return null;

    }

    @Override
    public String getCategory(String isbnumber) {
        if (isbnumber != null && !isbnumber.isEmpty()) {
            return booksdao.getCategory(isbnumber);
        }
        return null;
    }

    @Override
    public Date getPubDate(String isbnumber) {
        if (isbnumber != null && !isbnumber.isEmpty()) {
            return booksdao.getPubDate(isbnumber);
        }
        return null;
    }

    @Override
    public boolean getIsAvailable(String isbnumber) {
        if (isbnumber != null && !isbnumber.isEmpty()) {
            return booksdao.getIsAvailable(isbnumber);
        }
        return false;
    }

    @Override
    public String getRackno(int bookid) {
        if (bookid > 0) {
            return booksdao.getRackno(bookid);
        }
        return null;
    }

    @Override
    public boolean updateCategory(Category category) {
        return category != null ? booksdao.updateCategory(category) : false;
    }
   
}
