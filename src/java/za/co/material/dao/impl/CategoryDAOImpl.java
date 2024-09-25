package za.co.material.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Category;
import za.co.material.dao.CategoryDAO;

/**
 *
 * @author TRAIN 76
 */
public class CategoryDAOImpl implements CategoryDAO {

    private BasicDataSource dbm = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public CategoryDAOImpl(BasicDataSource dbm) {
        this.dbm = dbm;
    }
    // ****************************************************************************************
    @Override
    public boolean add(String category) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error: unable to get connection");
            return worked;
        }
        try {
            ps = con.prepareStatement("INSERT INTO category(cid, description, isActive) VALUES(null,?,1)");
            ps.setString(1, category);
            if (ps.executeUpdate() > 0) {
                worked = true;
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
        }
        return worked;
    }

       // ****************************************************************************************
    @Override
    public boolean delete(Category category) {
        boolean retVal = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE category SET isActive = 0 WHERE cid = ? ");
            ps.setInt(1, category.getcId());
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

       // ****************************************************************************************
    @Override
    public boolean update(Category category) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE category SET description=?, isActive=? WHERE cid=?");
            ps.setString(1, category.getDescription());
            ps.setBoolean(2, category.isIsActive());
            ps.setInt(3, category.getcId());
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

      // ****************************************************************************************
    @Override
    public Category get(int categoryId) {
        Category category = new Category();
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("SELECT cid,description,isActive FROM category WHERE cid = ?");
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            if (rs.next()) {
                category.setcId(rs.getInt("cid"));
                category.setDescription(rs.getString("description"));
                category.setIsActive(rs.getBoolean("isActive"));
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
        return category;
    }

      // ****************************************************************************************
    @Override
    public List<Category> getAll(boolean isActive) {
        List<Category> categoryList = new ArrayList<Category>();
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("SELECT cid,description,isActive FROM category WHERE isActive=?");
            ps.setBoolean(1, isActive);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category cat = new Category(rs.getInt("cid"), rs.getString("description"), rs.getBoolean("isActive"));
                categoryList.add(cat);
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
        return categoryList;
    }

    @Override
    public boolean setCategoryActivity(int cid, boolean isActive) {
        boolean worked = false;
        try {
            con = dbm.getConnection();
            ps = con.prepareStatement("UPDATE category set isActive = ? where cid = ?");
            ps.setBoolean(1, isActive);
            ps.setInt(2, cid);
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
  // ****************************************************************************************
}
