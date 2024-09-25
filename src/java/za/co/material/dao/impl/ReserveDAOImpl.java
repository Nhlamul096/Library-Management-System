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
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.dao.ReserveDAO;

/**
 *
 * @author TRAIN 76
 */
public class ReserveDAOImpl implements ReserveDAO {

    private BasicDataSource dbm = null;

    private Connection con = null;
    private Connection con1 = null;
    private Connection con2 = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null;
    private PreparedStatement ps3 = null;
    private ResultSet rs3 = null;
    private PreparedStatement ps4 = null;
    private ResultSet rs4 = null;

    public ReserveDAOImpl(BasicDataSource dbm) {
        this.dbm = dbm;
    }

    @Override
    public boolean reservebook(String title, String ISBNo, String memberid) {

        boolean worked = false;
        if (dbm != null) {
            try {
                con1 = dbm.getConnection();
                ps2 = con1.prepareStatement("INSERT INTO reservedbooks (bookid,ISBNo, memberid, Title, IsAvailable, DateReserved) VALUES (?,?,?,?,?,?)");
                ps2.setString(2, ISBNo);
                ps2.setString(3, memberid);
                ps2.setBoolean(5, false);
                ps2.setDate(6, Date.valueOf(LocalDate.now()));
                

                try {
                    con = dbm.getConnection();
                    ps = con.prepareStatement("SELECT bookid FROM bookitem WHERE ISBNo = ?");
                    ps.setString(1, ISBNo);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        ps2.setInt(1, rs.getInt("bookid"));
                    }
                } catch (SQLException ex) {
                    System.out.println("Error : " + ex.getMessage());
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            System.out.println("Error closing prepared statement : " + ex.getMessage());
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            System.out.println("Error closing connection : " + ex.getMessage());
                        }
                    }
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                            System.out.println("Error closing resultSet : " + ex.getMessage());
                        }
                    }
                }

                try {
                    con2 = dbm.getConnection();
                    ps3 = con2.prepareStatement("SELECT Title FROM books WHERE ISBNo = ?");
                    ps3.setString(1, ISBNo);
                    rs2 = ps3.executeQuery();
                    if (rs2.next()) {
                        ps2.setString(4, rs2.getString("Title"));
                    }
                } catch (SQLException ex) {
                    System.out.println("Error : " + ex.getMessage());
                } finally {
                    if (ps3 != null) {
                        try {
                            ps3.close();
                        } catch (SQLException ex) {
                            System.out.println("Error closing prepared statement : " + ex.getMessage());
                        }
                    }
                    if (con2 != null) {
                        try {
                            con2.close();
                        } catch (SQLException ex) {
                            System.out.println("Error closing connection : " + ex.getMessage());
                        }
                    }
                    if (rs2 != null) {
                        try {
                            rs2.close();
                        } catch (SQLException ex) {
                            System.out.println("Error closing resultSet : " + ex.getMessage());
                        }
                    }
                }

                if (ps2.executeUpdate() > 0) {
                    worked = true;
                }

            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            } finally {
                if (ps2 != null) {
                    try {
                        ps2.close();
                    } catch (SQLException ex) {
                        System.out.println("Error closing prepared statement : " + ex.getMessage());
                    }
                }
                if (con1 != null) {
                    try {
                        con1.close();
                    } catch (SQLException ex) {
                        System.out.println("Error closing connection: " + ex.getMessage());
                    }
                }
            }
        }
        return worked;
    }

    @Override
    public String checkreserve(String title, String ISBNo, String memberid) {

        boolean worked = false;
        String reserve = null;
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT IsAvailable FROM bookitem WHERE ISBNo = ?");
                ps.setString(1, ISBNo);
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        con1 = dbm.getConnection();
                        ps2 = con1.prepareStatement("UPDATE reservedbooks SET IsAvailable = ? WHERE ISBNo = ? AND Title = ? ");
                        ps2.setBoolean(1, rs.getBoolean("IsAvailable"));
                        ps2.setString(2, ISBNo);
                        ps2.setString(3, title);

                        if (ps2.executeUpdate() > 0) {
                            worked = true;
                            try {
                                con2 = dbm.getConnection();
                                ps3 = con2.prepareStatement("SELECT IsAvailable FROM reservedbooks WHERE ISBNo = ? AND memberid = ?");
                                ps3.setString(1, ISBNo);
                                ps3.setString(1, memberid);
                                rs2 = ps3.executeQuery();
                                if(rs2.next()){
                                    reserve = "Book name : "+title+" ISBNo : "+ISBNo+" isAvailable : "+rs2.getBoolean("IsAvailable");
                                }
                            } catch (SQLException ex) {
                                System.out.println("Error : " + ex.getMessage());
                            } finally {
                                if (ps3 != null) {
                                    try {
                                        ps3.close();
                                    } catch (SQLException ex) {
                                        System.out.println("Error closing prepared statement : " + ex.getMessage());
                                    }
                                }
                                if (con2 != null) {
                                    try {
                                        con2.close();
                                    } catch (SQLException ex) {
                                        System.out.println("Error closing connection : " + ex.getMessage());
                                    }
                                }
                                if (rs2 != null) {
                                    try {
                                        rs2.close();
                                    } catch (SQLException ex) {
                                        System.out.println("Error closing resultSet : " + ex.getMessage());
                                    }
                                }
                            }
                        }

                    } catch (SQLException ex) {
                        System.out.println("Error : " + ex.getMessage());
                    } finally {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (SQLException ex) {
                                System.out.println("Error closing prepared statement : " + ex.getMessage());
                            }
                        }
                        if (con1 != null) {
                            try {
                                con1.close();
                            } catch (SQLException ex) {
                                System.out.println("Error closing connection : " + ex.getMessage());
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Error closing prepared statement : " + ex.getMessage());
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Error closing connection : " + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Error closing resultSet : " + ex.getMessage());
                    }
                }
            }
        }
        return reserve;
    }

}
