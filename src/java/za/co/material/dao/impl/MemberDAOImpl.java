package za.co.material.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Member;
import za.co.material.dao.MemberDAO;

/**
 *
 * @author TRAIN 76
 */
public class MemberDAOImpl implements MemberDAO {

    private BasicDataSource dbm = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public MemberDAOImpl(BasicDataSource dbm) {
        this.dbm = dbm;
    }

    // **********************************************************************************************************
    @Override
    public Member getMember(String idNumber) {
        Member m = new Member();
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("SELECT m.IDNo,m.Name,m.Surname,m.Title,m.Address,m.TelNumber,m.CellNumber, m.Email, r.role FROM member AS m, login AS r \n"
                    + "WHERE m.IDNo = ? AND r.IDNo=m.IDNo");
            ps.setString(1, idNumber);
            rs = ps.executeQuery();
            if (rs.next()) {
                m.setIdNumber(rs.getString("IDNo"));
                m.setName(rs.getString("Name"));
                m.setSurname(rs.getString("Surname"));
                m.setTitle(rs.getString("Title"));
                m.setAddress(rs.getString("Address"));
                m.setTelNumber(rs.getString("TelNumber"));
                m.setCellNumber(rs.getString("CellNumber"));
                m.setEmail(rs.getString("Email"));
                m.setRole(rs.getInt("role"));
            }
        } catch (SQLException ex) {
            System.out.println("READING CODE ERROR :" + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("ResultSet closing failed error" + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Connection closing failed error" + ex.getMessage());
                }
            }
        }
        System.out.println(m);
        return m;
    }
// **********************************************************************************************************

    @Override
    public boolean addMember(Member member) {
        Connection con1 = null;
        boolean worked = false;
        try {
            con = dbm.getConnection();
            con1 = dbm.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error: unable to get connetction");
            return worked;
        }
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO member(IDNo, Name,Surname,Title,Address,TelNumber,CellNumber,Email) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, member.getIdNumber());
            ps.setString(2, member.getName());
            ps.setString(3, member.getSurname());
            ps.setString(4, member.getTitle());
            ps.setString(5, member.getAddress());
            ps.setString(6, member.getTelNumber());
            ps.setString(7, member.getCellNumber());
            ps.setString(8, member.getEmail());

            if (ps.executeUpdate() > 0) {
                if (con1 != null) {
                    try {
                        con1.setAutoCommit(false);
                        ps = con1.prepareStatement("INSERT INTO login(IDNo,Password,role) VALUES (?,?,?)");
                        ps.setString(1, member.getIdNumber());
                        ps.setString(2, member.getSurname());
                        ps.setInt(3, member.getRole());
                        if (ps.executeUpdate() > 0) {
                            con.commit();
                            con1.commit();
                            worked = true;
                        } else {
                            con.rollback();
                            con1.rollback();
                        }

                    } catch (SQLException se) {
                    }
                }
            } else {
                con.rollback();
            }
        } catch (SQLException ex) {
            System.out.println("INSERTING CODE ERROR :" + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Autocommit set error " + ex.getMessage());
                }
            }
            if (con1 != null) {
                try {
                    con1.setAutoCommit(true);
                    con1.close();
                } catch (SQLException ex) {
                    System.out.println("Autocommit set error " + ex.getMessage());
                }
            }
        }
        return worked;
    }

// **********************************************************************************************************    
    @Override
    public boolean updateMember(Member mem) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE member SET Name=?, Surname=?, Title=?, Address=?, TelNumber=?, CellNumber=?, Email=? WHERE IDNo=?");
            ps.setString(1, mem.getName());
            ps.setString(2, mem.getSurname());
            ps.setString(3, mem.getTitle());
            ps.setString(4, mem.getAddress());
            ps.setString(5, mem.getTelNumber());
            ps.setString(6, mem.getCellNumber());
            ps.setString(7, mem.getEmail());
            ps.setString(8, mem.getIdNumber());
            if (ps.executeUpdate() > 0) {
                worked = true;
            }
        } catch (SQLException ex) {
            System.out.println("Update CODE ERROR :" + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing connection: " + ex.getMessage());
                }
            }
        }
        return worked;
    }
// **********************************************************************************************************

    @Override
    public boolean deleteMember(String idNumber) {
        boolean retVal = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE member SET isActive = 0 WHERE IDNo = ? ");
            ps.setString(1, idNumber);
            if (ps.executeUpdate() > 0) {
                retVal = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing statement: " + ex.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing connectoin: " + ex.getMessage());
                }
            }
        }
        return retVal;
    }

    @Override
    public boolean setRole(String idNumber, int role) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE login set role = ? where IDNo = ?");

            ps.setInt(1, role);
            ps.setString(2, idNumber);

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
                    System.out.println("Error closing statement: " + ex.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing connectoin: " + ex.getMessage());
                }
            }
        }
        return worked;

    }

    @Override
    public boolean isValidMember(String idNum, String pw) {
        boolean retVal = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("SELECT m.IDNo FROM login AS lg , member AS m WHERE lg.IDNo=? AND lg.Password=? AND m.IDNo=lg.IDNo AND m.isActive=1");
            ps.setString(1, idNum);
            ps.setString(2, pw);
            rs = ps.executeQuery();
            if (rs.next()) {
                retVal = true;
            }
        } catch (SQLException ex) {
            System.out.println("READING CODE ERROR :" + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("ResultSet closing failed error" + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Connection closing failed error" + ex.getMessage());
                }
            }
        }
        return retVal;
    }

    @Override
    public Member changePassword(String idno, String password) {
        Member m = new Member();
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE login SET password=?  WHERE IDNo=?");
            ps.setString(1, password);
            ps.setString(2, idno);

            if (ps.executeUpdate() > 0) {
                worked = true;
            }
        } catch (SQLException ex) {
            System.out.println("Change Password CODE ERROR :" + ex.getMessage());

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing connection: " + ex.getMessage());
                }
            }
        }
        return m;
    }
}
