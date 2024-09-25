/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Author;
import za.co.material.dao.AuthorDAO;

/**
 *
 * @author TRAIN 76
 */
public class AuthorDAOImpl implements AuthorDAO {

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

    public AuthorDAOImpl(BasicDataSource dbm) {
        this.dbm = dbm;
    }

    @Override
    public boolean add(Author author) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("INSERT INTO authors (Aid, Author) VALUES(?,?)");
            ps.setInt(1, author.getAid());
            ps.setString(2, author.getAuthor());
            if (ps.executeUpdate() > 0) {
                worked = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {

                }
            }
        }
        return worked;
    }

    @Override
    public boolean delete(int aid) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("DELETE * FROM authors WHERE Aid = ?");
            ps.setInt(1, aid);
            if (ps.executeUpdate() > 0) {
                worked = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
        }
        return worked;
    }

    @Override
    public boolean update(Author author) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE authors SET Author = ? WHERE Aid = ?");
            ps.setString(1, author.getAuthor());
            ps.setInt(2, author.getAid());
            if (ps.executeUpdate() > 0) {
                worked = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
        }
        return worked;
    }

    @Override
    public Author get(int aid) {
        Author a = new Author();
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("SELECT Author FROM authors WHERE Aid = ? ");
            ps.setInt(1, aid);
            rs = ps.executeQuery();
            if (rs.next()) {
                a.setAid(aid);
                a.setAuthor(rs.getString("Author"));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
        }
        return a;
    }

    @Override
    public List<Author> getAll() {
        List<Author> list = new ArrayList<>();
        Author a = new Author();
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("SELECT Author, Aid FROM authors");
            rs = ps.executeQuery();
            while (rs.next()) {
                a.setAid(rs.getInt("Aid"));
                a.setAuthor(rs.getString("Author"));
                list.add(a);
            }

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }
        }
        return list;
    }
}
