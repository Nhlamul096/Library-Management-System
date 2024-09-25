
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

@WebListener
public class LibServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String url = sc.getInitParameter("url");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");
        String database = sc.getInitParameter("database");

        BasicDataSource datasource = new BasicDataSource();
        datasource.setUrl(url + database);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setMinIdle(5);
        datasource.setMaxIdle(10);
        sc.setAttribute("dbman", datasource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        BasicDataSource dbm = (BasicDataSource) sc.getAttribute("dbman");
        if (dbm != null) {
            try {
                dbm.close();
            } catch (SQLException ex) {
                System.out.println("Cannot close the database pool" + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        
        BasicDataSource datasource = new BasicDataSource();
        datasource.setUrl("jdbc:mysql://localhost:3306/" + "technolibrarydb");
        datasource.setUsername("root");
        datasource.setPassword("root");
        datasource.setMinIdle(5);
        datasource.setMaxIdle(10);
        try {
            //sc.setAttribute("dbman", datasource);
            Connection con = datasource.getConnection();
            System.out.println(con);
        } catch (SQLException ex) {
            Logger.getLogger(LibServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
