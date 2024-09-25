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
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.List;
import java.util.Random;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Book;
import za.co.material.dao.BooksDAO;
import za.co.material.dao.LoansDAO;

/**
 *
 * @author TRAIN 76
 */
public class LoansDAOImpl implements LoansDAO {

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
    private BooksDAO bookToDo;

    public LoansDAOImpl(BasicDataSource dbm) {
        this.dbm = dbm;
    }

    @Override
    public Book loanBook(String title, String IDNo) {
        boolean worked = false;
        boolean isAvailable = false;
        Book x = new Book();
        Random loanid = new Random();
        bookToDo = new BooksDAOImpl(dbm);
        List<Book> list = bookToDo.getBookByTitle(title);
        for (Book b : list) {
            isAvailable = b.getIsIsAvailable();
            if (isAvailable == true) {
                try {
                    con = dbm.getConnection();
                    ps = con.prepareStatement("INSERT INTO loans (loanid,bookid,IDNo,DueDate,returned,DateReturned,fines,DateLoaned) VALUES (?,?,?,?,?,?,?,?)");
                    ps.setInt(1, loanid.nextInt(99999 - 0) + 0);
                    ps.setInt(2, b.getBookid());
                    ps.setString(3, IDNo);
                    ps.setDate(4, Date.valueOf(LocalDate.now().plusDays(10)));
                    ps.setBoolean(5, false);
                    ps.setDate(6, null);
                    ps.setDouble(7, 0.0);
                    ps.setDate(8, Date.valueOf(LocalDate.now()));
                    if (ps.executeUpdate() > 0) {
                        bookToDo.updateBookAvailability(b.getBookid(), false);
                        worked = true;
                        x = b;
                        try {
                            boolean q = false;
                            con1 = dbm.getConnection();
                            ps2 = con1.prepareStatement("UPDATE reservedbooks SET IsAvailable = ? WHERE bookid = ? ");
                            ps2.setBoolean(1, q);
                            ps2.setInt(2, b.getBookid());
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
                                    System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
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
                        } catch (SQLException ex) {
                            System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                        }
                    }
                }
            }
        }
        return x;
    }

    @Override
    public Book returnBook(String title, String IDNo) {
        Book x = new Book();
        bookToDo = new BooksDAOImpl(dbm);
        List<Book> list = bookToDo.getBookByTitle(title);
        for (Book b : list) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT bookid FROM loans WHERE IDNo = ?");
                ps.setString(1, IDNo);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (b.getBookid() == rs.getInt("bookid")) {
                        try {
                            con1 = dbm.getConnection();
                            ps2 = con1.prepareStatement("SELECT DueDate,loanid FROM loans WHERE bookid = ?");
                            ps2.setInt(1, rs.getInt("bookid"));
                            rs2 = ps2.executeQuery();
                            if (rs2.next()) {
                                try {
                                    Connection con2 = dbm.getConnection();
                                    ps3 = con2.prepareStatement("UPDATE loans SET returned = ?, DateReturned = ?, fines = ?  WHERE loanid = ?");
                                    ps3.setBoolean(1, true);
                                    ps3.setDate(2, Date.valueOf(LocalDate.now()));
                                    ps3.setInt(4, rs2.getInt("loanid"));

                                    int days = (int) DAYS.between(rs2.getDate("DueDate").toLocalDate(), LocalDate.now());
                                    if (days >= 1 && days <= 4) {
                                        ps3.setDouble(3, 5.0);
                                    } else if (days >= 5 && days <= 10) {
                                        ps3.setDouble(3, 12.0);
                                    } else if (days > 10) {
                                        ps3.setDouble(3, 25.0);
                                    } else {
                                        ps3.setDouble(3, 0.0);
                                    }
                                    if (ps3.executeUpdate() > 0) {
                                        bookToDo.updateBookAvailability(b.getBookid(), true);
                                        b.setIsAvailable(true);
                                        x = b;
                                        try {
                                            boolean q = true;
                                            con1 = dbm.getConnection();
                                            ps2 = con1.prepareStatement("UPDATE reservedbooks SET IsAvailable = ? WHERE bookid = ? ");
                                            ps2.setBoolean(1, q);
                                            ps2.setInt(2, b.getBookid());
                                            if (ps2.executeUpdate() > 0) {
                                                String seeThis = null;
                                            }
                                        } catch (SQLException ex) {
                                            System.out.println("SQL Error : " + ex.getMessage());
                                        }
                                    }
                                    try {
                                        con2.close();
                                    } catch (SQLException ex) {
                                        System.out.println("Closing SQL connection Error : " + ex.getMessage());
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                                }
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQL Error : " + ex.getMessage());
                        } finally {
                            if (ps3 != null) {
                                try {
                                    ps3.close();
                                } catch (SQLException ex) {
                                    System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                                }
                            }
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
                                    System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                                }
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
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
            }
        }
        return x;
    }

    @Override
    public Book renewBook(String title, String IDNo) {
        Book x = new Book();
        boolean worked = false;
        bookToDo = new BooksDAOImpl(dbm);
        List<Book> list = bookToDo.getBookByTitle(title);
        for (Book b : list) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT bookid FROM loans WHERE IDNo = ?");
                ps.setString(1, IDNo);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (b.getBookid() == rs.getInt("bookid")) {
                        try {
                            con1 = dbm.getConnection();
                            ps2 = con1.prepareStatement("SELECT loanid, DueDate FROM loans WHERE bookid = ?");
                            ps2.setInt(1, rs.getInt("bookid"));
                            rs2 = ps2.executeQuery();
                            if (rs2.next()) {
                                ps3 = con.prepareStatement("UPDATE loans SET DueDate = ? WHERE loanid = ?");
                                ps3.setDate(1, Date.valueOf(rs2.getDate("DueDate").toLocalDate().plusDays(10)));
                                ps3.setInt(2, rs2.getInt("loanid"));
                                if (ps3.executeUpdate() > 0) {
                                    worked = true;
                                    x = b;
                                }
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
                                    System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                                }
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
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Closing SQL Prepared Statement Error : " + ex.getMessage());
                    }
                }
            }
        }
        return x;
    }

}
