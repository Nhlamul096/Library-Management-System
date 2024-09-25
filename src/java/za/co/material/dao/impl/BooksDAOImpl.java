/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Author;
import za.co.material.Book;
import za.co.material.Category;
import za.co.material.dao.BooksDAO;

/**
 *
 * @author Lungh
 */
public class BooksDAOImpl implements BooksDAO {

    private BasicDataSource dbm = null;

    private Connection con = null;
    private Connection con1 = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null;
    private PreparedStatement ps3 = null;
    private ResultSet rs3 = null;
    private PreparedStatement ps4 = null;
    private ResultSet rs4 = null;

    public BooksDAOImpl(BasicDataSource dbm) {
        this.dbm = dbm;
    }

    @Override
    public Book getBook(String isbnumber) {
        Book b = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT * FROM books WHERE ISBNo = ?");
                ps.setString(1, isbnumber);
                rs = ps.executeQuery();
                if (rs.next()) {
                    b.setISBNo(rs.getString("ISBNo"));
                    b.setTitle(rs.getString("title"));
                    b.setPubDate(rs.getDate("pubDate"));
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("SELECT description FROM category WHERE cid = ?");
                        ps2.setInt(1, rs.getInt("cid"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            b.setCategory(rs2.getString("description"));
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null) {
                    try {
                        rs.close();
                        rs2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return b;
    }

    @Override
    public Book getBook(int bookID) {
        Book b = new Book();
        Author a = new Author();
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT * FROM bookitem WHERE bookid = ?");
                ps.setInt(1, bookID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        b.setIsAvailable(rs.getBoolean("IsAvailable"));
                        b.setRackNo(rs.getString("RackNo"));
                        b.setBookid(bookID);
                        b.setISBNo(rs.getString("ISBNo"));
                        Connection con4 = dbm.getConnection();
                        ps4 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                        ps4.setString(1, rs.getString("ISBNo"));
                        rs4 = ps4.executeQuery();
                        if (rs4.next()) {
                            a.setAid(rs4.getInt("Aid"));
                            Connection con5 = dbm.getConnection();
                            PreparedStatement ps5 = con5.prepareStatement("SELECT Author FROM authors WHERE Aid = ? ");
                            ps5.setInt(1, rs4.getInt("Aid"));
                            ResultSet rs5 = ps5.executeQuery();
                            if (rs5.next()) {
                                a.setAuthor(rs5.getString("Author"));
                                b.setAuthors(a);
                            }
                        }
                        try {
                            con1 = dbm.getConnection();
                            ps2 = con1.prepareStatement("SELECT * FROM books WHERE ISBNo = ?");
                            ps2.setString(1, rs.getString("ISBNo"));
                            rs2 = ps2.executeQuery();
                            if (rs2.next()) {

                                b.setPubDate(rs2.getDate("PubDate"));
                                b.setTitle(rs2.getString("Title"));
                                try {
                                    Connection con3 = dbm.getConnection();
                                    ps3 = con3.prepareStatement("SELECT description FROM category WHERE cid = ?");
                                    ps3.setInt(1, rs2.getInt("cid"));
                                    rs3 = ps3.executeQuery();
                                    if (rs3.next()) {
                                        b.setCategory(rs3.getString("description"));
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("SQL Error : " + ex.getMessage());
                                }
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQL Error : " + ex.getMessage());
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }

                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null && ps3 != null) {
                    try {
                        ps.close();
                        ps2.close();
                        ps3.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null && rs3 != null) {
                    try {
                        rs.close();
                        rs2.close();
                        rs3.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return b;
    }

    @Override
    public List<String> getAuthor(String isbnumber) {
        List<String> list = new ArrayList<>();
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                ps.setString(1, isbnumber);
                rs = ps.executeQuery();
                while (rs.next()) {
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("SELECT Author FROM authors WHERE Aid = ?");
                        ps2.setInt(1, rs.getInt("Aid"));
                        rs2 = ps2.executeQuery();
                        list.add(rs.getString("Author"));
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null) {
                    try {
                        rs.close();
                        rs2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null /*&& con1 != null*/) {
                    try {
                        con.close();
                        //con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return list;
    }

    @Override
    public String getTitle(String isbnumber) {
        String title = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT title FROM books WHERE ISBNo = ?");
                ps.setString(1, isbnumber);
                rs = ps.executeQuery();
                if (rs.next()) {
                    title = rs.getString("title");
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return title;
    }

    @Override
    public String getCategory(String isbnumber) {
        String category = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT cid FROM books WHERE ISBNo = ?");
                ps.setString(1, category);
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("SELECT description FROM category WHERE cid = ?");
                        ps2.setInt(1, rs.getInt("cid"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            category = rs2.getString("description");
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null) {
                    try {
                        rs.close();
                        rs2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                        //con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return category;
    }

    @Override
    public Date getPubDate(String isbnumber) {
        Date pubDate = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT pubDate FROM books WHERE ISBNo = ?");
                ps.setString(1, isbnumber);
                rs = ps.executeQuery();
                if (rs.next()) {
                    pubDate = rs.getDate("pubDate");
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return pubDate;
    }

    @Override
    public boolean getIsAvailable(String isbnumber) {
        boolean isAvailable = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT IsAvailable FROM bookitem WHERE ISBNo = ?");
                ps.setString(1, isbnumber);
                rs = ps.executeQuery();
                if (rs.next()) {
                    isAvailable = rs.getBoolean("IsAvailable");
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return isAvailable;
    }

    @Override
    public String getRackno(int bookId) {
        String rackNo = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT RackNo FROM bookitem WHERE bookId = ?");
                ps.setInt(1, bookId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    rackNo = rs.getString("RackNo");
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return rackNo;
    }

    @Override
    public List<Book> getBookByAuthor(String authors) {
        List<Book> list = new ArrayList<>();
        Book b = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT Aid FROM authors WHERE Author = ?");
                ps.setString(1, authors);
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("SELECT ISBNo FROM booksauthor WHERE Aid = ?");
                        ps2.setInt(1, rs.getInt("Aid"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            try {
                                Connection con2 = dbm.getConnection();
                                ps3 = con2.prepareStatement("SELECT * FROM books WHERE ISBNo = ?");
                                ps3.setString(1, rs2.getString("ISBNo"));
                                rs3 = ps3.executeQuery();
                                if (rs3.next()) {
                                    b.setISBNo(rs3.getString("ISBNo"));
                                    b.setPubDate(rs3.getDate("pubDate"));
                                    b.setTitle(rs.getString("title"));
                                    try {
                                        Connection con3 = dbm.getConnection();
                                        ps4 = con3.prepareStatement("SELECT description FROM category WHERE cid = ?");
                                        ps4.setInt(1, rs3.getInt("cid"));
                                        rs4 = ps4.executeQuery();
                                        if (rs4.next()) {
                                            b.setCategory(rs4.getString("description"));
                                            list.add(b);
                                        }
                                    } catch (SQLException ex) {
                                        System.out.println("SQL Error : " + ex.getMessage());
                                    }
                                }
                            } catch (SQLException ex) {
                                System.out.println("SQL Error : " + ex.getMessage());
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null && ps3 != null && ps4 != null) {
                    try {
                        ps.close();
                        ps2.close();
                        ps3.close();
                        ps4.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null && rs3 != null && rs4 != null) {
                    try {
                        rs.close();
                        rs2.close();
                        rs3.close();
                        rs4.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null /*&& con1 != null*/) {
                    try {
                        con.close();
                        //con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        List<Book> list = new ArrayList<>();
        Book b = new Book();
        Author a = new Author();
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT * FROM books WHERE title = ?");
                ps.setString(1, title);
                rs = ps.executeQuery();
                while (rs.next()) {
                    b.setISBNo(rs.getString("ISBNo"));
                    b.setPubDate(rs.getDate("pubDate"));
                    b.setTitle(rs.getString("title"));
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("SELECT description FROM category WHERE cid = ?");
                        ps2.setInt(1, rs.getInt("cid"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            b.setCategory(rs2.getString("description"));
                            try {
                                Connection con2 = dbm.getConnection();
                                ps3 = con2.prepareStatement("SELECT * FROM bookitem WHERE ISBNo = ?");
                                ps3.setString(1, rs.getString("ISBNo"));
                                rs3 = ps3.executeQuery();
                                if (rs3.next()) {

                                    b.setBookid(rs3.getInt("bookid"));
                                    b.setIsAvailable(rs3.getBoolean("IsAvailable"));
                                    b.setRackNo(rs3.getString("RackNo"));

                                    try {
                                        Connection con3 = dbm.getConnection();
                                        ps4 = con3.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                        ps4.setString(1, rs.getString("ISBNo"));
                                        rs4 = ps4.executeQuery();
                                        if (rs4.next()) {
                                            a.setAid(rs4.getInt("Aid"));
                                            PreparedStatement ps5 = con3.prepareStatement("SELECT Author FROM authors WHERE Aid = ?");
                                            ps5.setInt(1, rs4.getInt("Aid"));
                                            ResultSet rs5 = ps5.executeQuery();
                                            if (rs5.next()) {
                                                a.setAuthor(rs5.getString("Author"));
                                                b.setAuthors(a);
                                                list.add(b);
                                            }
                                        }

                                    } catch (SQLException ex) {
                                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                                    }
                                }
                            } catch (SQLException ex) {
                                System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                            }

                        }
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null) {
                    try {
                        rs.close();
                        rs2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<Book> getBookByCategory(int category) {
        List<Book> list = new ArrayList<>();
        Book b = new Book();
        Author a = new Author();
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT description FROM category WHERE cid = ?");
                ps.setInt(1, category);
                rs = ps.executeQuery();
                if (rs.next()) {
                    b.setCategory(rs.getString("description"));
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("SELECT * FROM books WHERE cid = ?");
                        ps2.setInt(1, category);
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {

                            b.setISBNo(rs2.getString("ISBNo"));
                            b.setPubDate(rs2.getDate("PubDate"));
                            b.setTitle(rs2.getString("Title"));

                            try {
                                Connection con2 = dbm.getConnection();
                                ps3 = con2.prepareStatement("SELECT * FROM bookitem WHERE ISBNO = ?");
                                ps3.setString(1, rs2.getString("ISBNo"));
                                rs3 = ps3.executeQuery();
                                if (rs3.next()) {
                                    b.setBookid(rs3.getInt("bookid"));
                                    b.setIsAvailable(rs3.getBoolean("IsAvailable"));
                                    b.setRackNo(rs3.getString("RackNo"));
                                    try {
                                        Connection con3 = dbm.getConnection();
                                        ps4 = con3.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                                        ps4.setString(1, rs2.getString("ISBNo"));
                                        rs4 = ps4.executeQuery();
                                        if (rs4.next()) {
                                            try {
                                                a.setAid(rs4.getInt("Aid"));
                                                PreparedStatement ps5 = con3.prepareStatement("SELECT Author FROM authors WHERE Aid = ?");
                                                ps5.setInt(1, rs4.getInt("Aid"));
                                                ResultSet rs5 = ps5.executeQuery();
                                                if (rs5.next()) {
                                                    a.setAuthor(rs5.getString("Author"));
                                                    b.setAuthors(a);
                                                    list.add(b);

                                                    try {
                                                        ps5.close();
                                                        rs5.close();
                                                    } catch (SQLException ex) {
                                                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                                                    }

                                                }
                                            } catch (SQLException ex) {
                                                System.out.println("SQL Error : " + ex.getMessage());
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        System.out.println("SQL Error : " + ex.getMessage());
                                    }
                                }

                            } catch (SQLException ex) {
                                System.out.println("SQL Error : " + ex.getMessage());
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                        ps3.close();
                        ps4.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null) {
                    try {
                        rs.close();
                        rs2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<Book> getBookByPubDate(Date pubDate) {
        List<Book> list = new ArrayList<>();
        Book b = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT * FROM books WHERE pubDate = ?");
                ps.setDate(1, pubDate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    b.setISBNo(rs.getString("ISBNo"));
                    b.setPubDate(pubDate);
                    b.setTitle(rs.getString("title"));
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("SELECT description FROM category WHERE cid = ?");
                        ps2.setInt(1, rs.getInt("cid"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            b.setCategory(rs2.getString("description"));
                            list.add(b);
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null && rs2 != null) {
                    try {
                        rs.close();
                        rs2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return list;
    }

    @Override
    public boolean addBook(Book book, int categoryId) {
        Book b = book;
        Author a = b.getAuthors();
        int cid = categoryId;
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("INSERT INTO books (ISBNo,title,cid,pubDate) VALUES (?,?,?,?)");

                ps.setString(1, b.getISBNo());
                ps.setString(2, b.getTitle());
                ps.setInt(3, cid);
                ps.setDate(4, b.getPubDate());
                if (ps.executeUpdate() > 0) {

                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("INSERT INTO bookitem (bookId, ISBNo, IsAvailable, RackNo) VALUES (?,?,?,?)");
                        ps2.setInt(1, book.getBookid());
                        ps2.setString(2, book.getISBNo());
                        ps2.setBoolean(3, book.getIsIsAvailable());
                        ps2.setString(4, book.getRackNo());
                        if (ps2.executeUpdate() > 0) {
                            try {
                                Connection con3 = dbm.getConnection();
                                PreparedStatement ps5 = con3.prepareStatement("SELECT Aid FROM Authors WHERE Aid = ? ");
                                ps5.setInt(1, a.getAid());
                                ResultSet rs5 = ps5.executeQuery();
                                if (rs5.next()) {
                                    try {
                                        ps4 = con3.prepareStatement("INSERT INTO booksauthor (Aid, ISBNo) VALUES (?,?)");
                                        ps4.setInt(1, a.getAid());
                                        ps4.setString(2, b.getISBNo());

                                        if (ps4.executeUpdate() > 0) {
                                            worked = true;
                                        }
                                    } catch (SQLException ex) {
                                        System.out.println("SQL Error : " + ex.getMessage());
                                    }
                                } else {
                                    try {
                                        Connection con2 = dbm.getConnection();
                                        ps3 = con2.prepareStatement("INSERT INTO authors (Aid, Author) VALUES (?,?) ");
                                        a = b.getAuthors();
                                        ps3.setInt(1, a.getAid());
                                        ps3.setString(2, a.getAuthor());
                                        if (ps3.executeUpdate() > 0) {
                                            ps4 = con3.prepareStatement("INSERT INTO booksauthor (Aid, ISBNo) VALUES (?,?)");
                                            ps4.setInt(1, a.getAid());
                                            ps4.setString(2, b.getISBNo());

                                            if (ps4.executeUpdate() > 0) {
                                                worked = true;
                                            }
                                        }

                                    } catch (SQLException ex) {
                                        System.out.println("SQL Error : " + ex.getMessage());
                                    }
                                }
                            } catch (SQLException ex) {
                                System.out.println("SQL Error : " + ex.getMessage());
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    } finally {
                        if (con1 != null) {
                            try {
                                con1.close();
                            } catch (SQLException ex) {
                                System.out.println("Closing Connection Error : " + ex.getMessage());
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                        ps2.close();
                        ps4.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
                if (ps3 != null) {
                    try {
                        ps3.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public boolean updateBook(Book book) {
        boolean worked = false;
        Author a = new Author();
        a = book.getAuthors();
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps4 = con.prepareStatement("SELECT ISBNo FROM bookitem WHERE bookid = ?");
                ps4.setInt(1, book.getBookid());
                rs = ps4.executeQuery();
                if (rs.next()) {
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("UPDATE books SET title = ?, cid = ?, pubDate =? WHERE ISBNo = ?");
                        ps2.setString(1, book.getTitle());
                        ps2.setInt(2, Integer.parseInt(book.getCategory()));
                        ps2.setDate(3, book.getPubDate());
                        ps2.setString(4, rs.getString("ISBNo"));

                        if (ps2.executeUpdate() > 0) {
                            try {
                                Connection con2 = dbm.getConnection();
                                ps = con2.prepareStatement("UPDATE bookitem SET RackNo = ?, IsAvailable = ? WHERE ISBNo = ?");
                                ps.setString(1, book.getRackNo());
                                ps.setBoolean(2, book.getIsIsAvailable());
                                //ps.setInt(3, book.getBookid());
                                ps.setString(3, book.getISBNo());
                                if (ps.executeUpdate() > 0) {
                                    try {
                                        Connection con3 = dbm.getConnection();
                                        ps3 = con3.prepareStatement("UPDATE booksauthor SET Aid = ? WHERE ISBNo =? ");
                                        ps3.setInt(1, a.getAid());
                                        ps3.setString(2, book.getISBNo());
                                        if (ps3.executeUpdate() > 0) {
                                            worked = true;

                                        }
                                    } catch (SQLException ex) {
                                        System.out.println("SQL Error : " + ex.getMessage());
                                        try {
                                            Connection con4 = dbm.getConnection();
                                            ps4 = con4.prepareStatement("INSERT INTO authors (Aid, Author) VALUES (?,?) ");
                                            ps4.setInt(1, a.getAid());
                                            ps4.setString(2, a.getAuthor());
                                            if (ps4.executeUpdate() > 0) {
                                                try {
                                                    Connection con5 = dbm.getConnection();
                                                    ps3 = con5.prepareStatement("UPDATE booksauthor SET Aid = ? WHERE ISBNo =? ");
                                                    ps3.setInt(1, a.getAid());
                                                    ps3.setString(2, book.getISBNo());
                                                    if (ps3.executeUpdate() > 0) {
                                                        worked = true;

                                                    }
                                                } catch (SQLException exs) {
                                                }
                                            }
                                        } catch (SQLException exs) {
                                            System.out.println("SQL Error : " + ex.getMessage());
                                        }
                                    }
                                }
                            } catch (SQLException ex) {
                                System.out.println("SQL Error : " + ex.getMessage());
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());

            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                        ps3.close();
                        ps4.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public boolean updateBookAuthor(String isbnumber, String authors
    ) {
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con1.prepareStatement("SELECT Aid FROM authors WHERE Author = ?");
                ps.setString(1, authors);
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("UPDATE booksauthor SET Aid = ? WHERE ISBNo = ?");
                        ps2.setInt(1, rs.getInt("Aid"));
                        ps2.setString(2, isbnumber);
                        if (ps2.executeUpdate() > 0) {
                            worked = true;
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQL Error : " + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null && ps2 != null) {
                    try {
                        ps.close();
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL ResultSet Error : " + ex.getMessage());
                    }
                }
                if (con != null && con1 != null) {
                    try {
                        con.close();
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public boolean updateBookTitle(String isbnumber, String title) {
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("UPDATE books SET title = ? WHERE ISBNo = ?");
                ps.setString(1, title);
                ps.setString(2, isbnumber);
                if (ps.executeUpdate() > 0) {
                    worked = true;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public boolean updateBookCategory(String isbnumber, int category) {
        boolean worked = false;
        if (dbm != null) {

            try {
                con1 = dbm.getConnection();
                ps2 = con1.prepareStatement("UPDATE books SET cid = ? WHERE ISBNo = ?");
                ps2.setInt(1, category);
                ps2.setString(2, isbnumber);
                if (ps2.executeUpdate() > 0) {
                    worked = true;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps2 != null) {
                    try {
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con1 != null) {
                    try {
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }

        return worked;
    }

    @Override
    public boolean updateBookAvailability(int bookID, boolean isAvailable
    ) {
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("UPDATE bookitem SET IsAvailable = ? WHERE bookId = ?");
                ps.setBoolean(1, isAvailable);
                ps.setInt(2, bookID);
                if (ps.executeUpdate() > 0) {
                    worked = true;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public boolean updateBookRackNo(int bookId, String rackNo
    ) {
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("UPDATE bookitem SET RackNo = ? WHERE bookId = ?");
                ps.setString(1, rackNo);
                ps.setInt(2, bookId);
                if (ps.executeUpdate() > 0) {
                    worked = true;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public boolean updateBookPubDate(String isbnumber, Date pubDate
    ) {
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("UPDATE books SET pubDate = ? WHERE ISBNo = ?");
                ps.setDate(1, pubDate);
                ps.setString(2, isbnumber);
                if (ps.executeUpdate() > 0) {
                    worked = true;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public boolean deleteBook(int bookId
    ) {
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("DELETE FROM bookitem WHERE bookId = ?");
                ps.setInt(1, bookId);
                if (ps.executeUpdate() > 0) {
                    worked = true;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;

    }

    @Override
    public boolean updateCategory(Category category) {
        boolean worked = false;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("UPDATE category SET description = ?, isActive = ? WHERE cid = ?");
                ps.setString(1, category.getDescription());
                ps.setBoolean(2, category.isIsActive());
                ps.setInt(3, category.getcId());
                if (ps.executeUpdate() > 0) {
                    worked = true;
                }
            } catch (SQLException ex) {
                System.out.println("SQL Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing Connection Error : " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

}
