
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Member;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TRAIN 76
 */
public class Test {

    public static void main(String[] args) {

        //Member m = (Member) session.getAttribute("member");
        String memberid = "1234512345123";//m.getIdNumber();
        String isbno = null;
        String title = null;
        String category = null;
        String author = null;
        LocalDate date = null;
        int bookid = 0;
        boolean returned = false;
        int aid = 0;

        PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null, ps6 = null;
        Connection con = null, con2 = null, con3 = null, con4 = null, con5 = null, con6 = null;
        ResultSet rs = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null, rs6 = null;
        Statement stm = null, stm2 = null;
        BasicDataSource dbm = new BasicDataSource();
        dbm.setUrl("jdbc:mysql://localhost:3306/" + "technolibrarydb");
        dbm.setUsername("root");
        dbm.setPassword("root");
        dbm.setMinIdle(50);
        dbm.setMaxIdle(100);

        

    }
}
