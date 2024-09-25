/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.process.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Author;
import za.co.material.Book;
import za.co.material.service.BooksService;
import za.co.material.service.impl.BooksServiceImpl;
import za.co.process.ProcessInt;

/**
 *
 * @author thiof
 */
public class BookProcess implements ProcessInt {

    BasicDataSource dbmanager;
    BooksService booksdao;

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            dbmanager = (BasicDataSource) sc.getAttribute("dbman");
        }
        if (dbmanager == null) {
            return;
        }
        booksdao = new BooksServiceImpl(dbmanager);
        String action = request.getParameter("action");
        List<Book> book = new ArrayList<>();
        String isbno = null;
        String title = null;
        String author = null;
        int category = 0;
        Date pubDate = null;
        String rackNo = null;
        int catId = 1;
        int bookid = 0;
        boolean isAvailable = true;
        Book b = null;

        if (action != null) {
            switch (action.toLowerCase()) {
                case "add":
                    try {
                    Author a = new Author();
                    a.setAid(Integer.parseInt(request.getParameter("aid")));
                    a.setAuthor(request.getParameter("author"));
                    b = new Book(request.getParameter("isbno"), request.getParameter("title"),
                            request.getParameter("category"),
                            Date.valueOf(request.getParameter("pubDate")), request.getParameter("rackNo"),
                            Boolean.valueOf(request.getParameter("isAvailable")),
                            Integer.parseInt(request.getParameter("bookid")), a);
                } catch (NumberFormatException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

                try {
                    catId = Integer.parseInt(String.valueOf(request.getParameter("category")));  // return a number 
                } catch (NumberFormatException nfe) {
                    System.out.println("Error " + nfe.getMessage());
                }
                if (booksdao.addBook(b, catId)) {

                    book.add(b);
                    request.setAttribute("book", book);
                    request.setAttribute("msg", "the book has been added");
                } else {
                    request.setAttribute("book", null);
                    request.setAttribute("msg", "the book has NOT BEEN added. Error");
                }
                processTheResponse(request, response);
                break;

                case "update":

                    Author a = new Author();

                    try {
                        bookid = Integer.parseInt(request.getParameter("bookid"));
                        a.setAid(Integer.parseInt(request.getParameter("aid")));
                        a.setAuthor(request.getParameter("author"));
                        b = new Book(request.getParameter("isbno"), request.getParameter("title"),
                                request.getParameter("category"),
                                Date.valueOf(request.getParameter("pubDate")), request.getParameter("rackNo"),
                                Boolean.valueOf(request.getParameter("isAvailable")),
                                Integer.parseInt(request.getParameter("bookid")), a);
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } catch (NullPointerException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }

                    try {
                        catId = Integer.parseInt(request.getParameter("category"));  // return a number 
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error " + nfe.getMessage());
                    }
                    if (bookid > 0) {
                        if (booksdao.updateBook(b)) {
                            book.add(b);
                            request.setAttribute("book", book);
                            request.setAttribute("msg", "the book has been updated");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book has NOT BEEN updated. Error");
                        }
                    } else if (isbno != null && author != null) {

                        if (booksdao.updateBookAuthor(isbno, author)) {
                            for (Book c : booksdao.getBookByAuthor(author)) {
                                book.add(c);
                            }
                            request.setAttribute("book", book);
                            request.setAttribute("msg", "the book has been updated");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book has NOT BEEN updated. Error");
                        }
                    } else if (isbno != null && title != null) {

                        if (booksdao.updateBookTitle(isbno, title)) {
                            for (Book c : booksdao.getBookByTitle(title)) {
                                book.add(c);
                            }
                            request.setAttribute("book", book);
                            request.setAttribute("msg", "the book has been updated");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book has NOT BEEN updated. Error");
                        }
                    } else if (isbno != null && category > 0) {

                        if (booksdao.updateBookCategory(isbno, catId)) {
                            for (Book c : booksdao.getBookByCategory(catId)) {
                                book.add(c);
                            }
                            request.setAttribute("book", book);
                            request.setAttribute("msg", "the book has been updated");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book has NOT BEEN updated. Error");
                        }
                    }
                    processTheResponse(request, response);
                    break;

                case "delete":

                    bookid = 0;
                    title = request.getParameter("title");
                    try {
                        bookid = Integer.parseInt(request.getParameter("bookid"));

                    } catch (NumberFormatException ex) {

                    }

                    if (bookid > 0) {
                        b = booksdao.getBook(bookid);
                        if (booksdao.deleteBook(bookid)) {
                            book.add(b);
                            request.setAttribute("book", book);
                            request.setAttribute("msg", "the book has been deleted");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book cannot be found. Error");
                        }
                    } else if (title != null) {
                        List<Book> bookList = booksdao.getBookByTitle(title);
                        for (Book bb : bookList) {
                            int id = bb.getBookid();
                            if (booksdao.deleteBook(id)) {
                                request.setAttribute("book", bookList);
                                request.setAttribute("msg", "the book has been deleted");
                            } else {
                                request.setAttribute("book", null);
                                request.setAttribute("msg", "the book cannot be found. Error");
                            }
                        }
                    }
                    processTheResponse(request, response);
                    break;

                case "get":
                    bookid = 0;
                    title = request.getParameter("title");
                    author = request.getParameter("author");
                    //pubDate = Date.valueOf(request.getParameter("pubDate"));
                    try {
                        category = Integer.parseInt(String.valueOf(request.getParameter("category")));
                        bookid = Integer.parseInt(request.getParameter("bookid"));
                    } catch (NumberFormatException ex) {
                    }
                    if (bookid > 0) {
                        b = booksdao.getBook(bookid);
                        List<Book> bbooks = new ArrayList<>();
                        bbooks.add(b);
                        if (bbooks != null) {
                            request.setAttribute("book", bbooks);
                            request.setAttribute("msg", "the book is: ");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book cannot be found. Error");
                        }
                    } else if (title != null && !title.isEmpty() && !title.equals("")) {
                        List<Book> bbooks = booksdao.getBookByTitle(title);

                        if (bbooks != null) {
                            request.setAttribute("book", bbooks);
                            request.setAttribute("msg", "the book is: ");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book cannot be found. Error");
                        }

                    } else if (author != null && !author.isEmpty() && !author.equals("")) {
                        List<Book> bbooks = booksdao.getBookByAuthor(author);

                        if (bbooks != null) {
                            request.setAttribute("book", bbooks);
                            request.setAttribute("msg", "the book is: ");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book cannot be found. Error");
                        }
                    } else if (category > 0) {
                        List<Book> bbooks = booksdao.getBookByCategory(category);
                        if (bbooks != null) {
                            request.setAttribute("book", bbooks);
                            request.setAttribute("msg", "the book is: ");
                        } else {
                            request.setAttribute("book", null);
                            request.setAttribute("msg", "the book cannot be found. Error");
                        }
                    }
                    processTheResponse(request, response);
                    break;

                default:
            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher view = request.getRequestDispatcher("BooksTable.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
        }
    }

}
