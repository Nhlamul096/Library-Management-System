/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.dao.impl;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Author;
import za.co.material.Book;
import za.co.material.Member;
import za.co.material.dao.ReportDAO;

/**
 *
 * @author TRAIN 76
 */
public class ReportDAOImpl implements ReportDAO {

    private BasicDataSource dbm = null;
    private Connection con = null;
    private Connection con3 = null;
    private Connection con2 = null;
    private Connection con4 = null;
    private PreparedStatement ps = null;
    private PreparedStatement ps2 = null;
    private PreparedStatement ps3 = null;
    private PreparedStatement ps4 = null;
    private Statement stm = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    private ResultSet rs3 = null;
    private ResultSet rs4 = null;

    public ReportDAOImpl(BasicDataSource dbm) {
        this.dbm = dbm;
    }

    @Override
    public List<Book> booksLoaned(List<Date> dateLoaned) {
        List<Book> booksl = new ArrayList<>();

        for (Date d : dateLoaned) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT bookid FROM loans WHERE DateLoaned = ?");
                ps.setDate(1, d);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Book bb = new Book();
                    Author aa = new Author();
                    con2 = dbm.getConnection();
                    ps2 = con2.prepareStatement("SELECT * FROM bookitem WHERE bookid = ?");
                    ps2.setInt(1, rs.getInt("bookid"));
                    rs2 = ps2.executeQuery();
                    if (rs2.next()) {

                        bb.setBookid(rs2.getInt("bookid"));
                        bb.setIsAvailable(rs2.getBoolean("IsAvailable"));
                        bb.setISBNo(rs2.getString("ISBNo"));
                        bb.setRackNo(rs2.getString("RackNo"));

                        try {
                            con3 = dbm.getConnection();
                            ps3 = con3.prepareStatement("SELECT * FROM books WHERE ISBNo = ?");
                            ps3.setString(1, rs2.getString("ISBNo"));
                            rs3 = ps3.executeQuery();
                            if (rs3.next()) {

                                bb.setTitle(rs3.getString("Title"));
                                bb.setPubDate(rs3.getDate("PubDate"));
                                try {

                                    PreparedStatement ps4 = con3.prepareStatement("SELECT description FROM category WHERE cid = ?");
                                    ps4.setInt(1, rs3.getInt("cid"));
                                    ResultSet rs4 = ps4.executeQuery();
                                    if (rs4.next()) {
                                        bb.setCategory(rs4.getString("description"));
                                    }
                                } catch (SQLException ex) {
                                    System.out.println("READING CODE ERROR :" + ex.getMessage());
                                }
                            }
                        } catch (SQLException ex) {
                            System.out.println("READING CODE ERROR :" + ex.getMessage());
                        } finally {
                            if (ps3 != null) {
                                try {
                                    ps3.close();
                                } catch (SQLException ex) {
                                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                                }
                            }
                            if (rs3 != null) {
                                try {
                                    rs3.close();
                                } catch (SQLException ex) {
                                    System.out.println("ResultSet closing failed error" + ex.getMessage());
                                }
                            }
                            if (con3 != null) {
                                try {
                                    con3.close();
                                } catch (SQLException ex) {
                                    System.out.println("ResultSet closing failed error" + ex.getMessage());
                                }
                            }
                        }

                        try {
                            con4 = dbm.getConnection();
                            PreparedStatement ps5 = con4.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ?");
                            ps5.setString(1, rs2.getString("ISBNo"));
                            ResultSet rs5 = ps5.executeQuery();
                            if (rs5.next()) {
                                aa.setAid(rs5.getInt("Aid"));
                                PreparedStatement ps6 = con4.prepareStatement("SELECT Author FROM authors WHERE Aid = ?");
                                ps6.setInt(1, rs5.getInt("Aid"));
                                ResultSet rs6 = ps6.executeQuery();
                                if (rs6.next()) {
                                    aa.setAuthor(rs6.getString("Author"));
                                    bb.setAuthors(aa);
                                }
                                try {
                                    ps6.close();
                                } catch (SQLException ex) {
                                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                                }
                            }
                            try {
                                ps5.close();
                            } catch (SQLException ex) {
                                System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                            }

                        } catch (SQLException ex) {
                            System.out.println("READING CODE ERROR :" + ex.getMessage());
                        } finally {
                            if (con4 != null) {
                                try {
                                    con4.close();
                                } catch (SQLException ex) {
                                    System.out.println("ResultSet closing failed error" + ex.getMessage());
                                }
                            }
                        }
                    }
                    booksl.add(bb);
                }
            } catch (SQLException ex) {
                System.out.println("READING CODE ERROR :" + ex.getMessage());
            }
        }
        return booksl;
    }

    @Override
    public Document overdueLoans(List<Date> dueDate, String category) {
        Document document = null;
        List<String> list = new ArrayList<>();
        List<Member> members = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Member m = new Member();
        Book bb = new Book();
        Author aa = new Author();
        XYZ:
        for (Date d : dueDate) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT bookid,IDNo FROM loans WHERE DueDate = ? AND returned = ? ");
                ps.setDate(1, d);
                ps.setBoolean(2, false);
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        con2 = dbm.getConnection();
                        ps2 = con2.prepareStatement("SELECT * FROM bookitem WHERE bookid = ?");
                        ps2.setInt(1, rs.getInt("bookid"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            bb.setBookid(rs.getInt("bookid"));
                            bb.setISBNo(rs2.getString("ISBNo"));
                            bb.setIsAvailable(rs2.getBoolean("IsAvailable"));
                            bb.setRackNo(rs2.getString("RackNo"));
                            try {
                                con3 = dbm.getConnection();
                                ps3 = con.prepareStatement("SELECT * FROM books WHERE ISBNo = ?");
                                ps3.setString(1, rs2.getString("ISBNo"));
                                rs3 = ps3.executeQuery();
                                if (rs3.next()) {
                                    bb.setTitle(rs3.getString("Title"));
                                    bb.setPubDate(rs3.getDate("PubDate"));
                                    try {
                                        con4 = dbm.getConnection();
                                        ps4 = con.prepareStatement("SELECT description FROM category WHERE cid = ?");
                                        ps4.setInt(1, rs3.getInt("cid"));
                                        rs4 = ps4.executeQuery();
                                        if (rs4.next()) {
                                            if (category.equalsIgnoreCase(rs4.getString("description"))) {
                                                bb.setCategory(category);
                                            } else {
                                                continue XYZ;
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        System.out.println("READING CODE ERROR :" + ex.getMessage());
                                    } finally {
                                        if (con4 != null) {
                                            try {
                                                con4.close();
                                            } catch (SQLException ex) {
                                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                                            }
                                        }
                                        if (rs4 != null) {
                                            try {
                                                rs4.close();
                                            } catch (SQLException ex) {
                                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                                            }
                                        }
                                        if (ps4 != null) {
                                            try {
                                                ps4.close();
                                            } catch (SQLException ex) {
                                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                                            }
                                        }
                                    }
                                    try {
                                        Connection con5 = dbm.getConnection();
                                        PreparedStatement ps5 = con5.prepareStatement("SELECT Aid FROM booksauthor WHERE ISBNo = ? ");
                                        ps5.setString(1, rs2.getString("ISBNo"));
                                        ResultSet rs5 = ps5.executeQuery();
                                        if (rs5.next()) {
                                            try {
                                                PreparedStatement ps6 = con5.prepareStatement("SELECT * FROM authors WHERE Aid = ? ");
                                                ps6.setInt(1, rs5.getInt("Aid"));
                                                ResultSet rs6 = ps6.executeQuery();
                                                if (rs6.next()) {
                                                    aa.setAid(rs5.getInt("Aid"));
                                                    aa.setAuthor(rs6.getString("Author"));
                                                    bb.setAuthors(aa);
                                                    books.add(bb);
                                                }
                                                try {
                                                    ps6.close();
                                                } catch (SQLException ex) {
                                                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                                                }

                                            } catch (SQLException ex) {
                                                System.out.println("READING CODE ERROR :" + ex.getMessage());
                                            }
                                        }

                                        try {
                                            ps5.close();
                                        } catch (SQLException ex) {
                                            System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                                        }
                                        try {
                                            con5.close();
                                        } catch (SQLException ex) {
                                            System.out.println("Connection closing failed error" + ex.getMessage());
                                        }

                                    } catch (SQLException ex) {
                                        System.out.println("READING CODE ERROR :" + ex.getMessage());
                                    }
                                }
                            } catch (SQLException ex) {
                                System.out.println("READING CODE ERROR :" + ex.getMessage());
                            } finally {
                                if (con3 != null) {
                                    try {
                                        con3.close();
                                    } catch (SQLException ex) {
                                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                                    }
                                }
                                if (rs3 != null) {
                                    try {
                                        rs3.close();
                                    } catch (SQLException ex) {
                                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                                    }
                                }
                                if (ps3 != null) {
                                    try {
                                        ps3.close();
                                    } catch (SQLException ex) {
                                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                                    }
                                }
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("READING CODE ERROR :" + ex.getMessage());
                    } finally {
                        if (con2 != null) {
                            try {
                                con2.close();
                            } catch (SQLException ex) {
                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                            }
                        }
                        if (rs2 != null) {
                            try {
                                rs2.close();
                            } catch (SQLException ex) {
                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                            }
                        }
                        if (ps2 != null) {
                            try {
                                ps2.close();
                            } catch (SQLException ex) {
                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                            }
                        }
                    }

                    try {
                        Connection con6 = dbm.getConnection();
                        PreparedStatement ps7 = con6.prepareStatement("SELECT * FROM member WHERE IDNo = ?");
                        ps7.setString(1, rs.getString("IDNo"));
                        ResultSet rs7 = ps7.executeQuery();
                        if (rs7.next()) {

                            m.setTitle(rs7.getString("Title"));
                            m.setAddress(rs7.getString("Address"));
                            m.setCellNumber(rs7.getString("CellNumber"));
                            m.setEmail(rs7.getString("Email"));
                            m.setIdNumber(rs7.getString("IDNo"));
                            m.setName(rs7.getString("Name"));
                            m.setRole(rs7.getInt("role"));
                            m.setSurname(rs7.getString("Surname"));
                            m.setTelNumber(rs7.getString("TelNumber"));

                            members.add(m);
                        }
                        try {
                            ps7.close();
                        } catch (SQLException ex) {
                            System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                        }
                        try {
                            con6.close();
                        } catch (SQLException ex) {
                            System.out.println("Connection closing failed error" + ex.getMessage());
                        }
                        list.add(bb.toString() + "  IS WITH  " + m.toString());
                    } catch (SQLException ex) {
                        System.out.println("READING CODE ERROR :" + ex.getMessage());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("READING CODE ERROR :" + ex.getMessage());
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                    }
                }
            }

        }
        {
            Properties prop = new Properties();

            try {
                prop.load(new FileReader("C:\\Users\\TRAIN 76\\Desktop\\TechnoLib\\Location.properties"));
            } catch (IOException ex) {
                System.out.println("Error loading : " + ex.getMessage());
            }

            try {
                String dest = prop.getProperty("overdueloans");
                PdfWriter writer;

                writer = new PdfWriter(dest);

                PdfDocument pdfDoc = new PdfDocument(writer);
                pdfDoc.addNewPage();
                document = new Document(pdfDoc);

                Paragraph paragraph = null;

                try {
                    paragraph = new Paragraph(" OVERDUE LOANS REPORT ").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))//(StandardFonts.HELVETICA))
                            .setFontSize(30F)
                            .setUnderline()
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER);
                } catch (IOException ex) {
                    System.out.println("Error :" + ex.getMessage());
                }

                document.add(paragraph);

                Paragraph para = new Paragraph(new java.util.Date().toString())
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(12F);
                document.add(para);

                Paragraph para1 = new Paragraph("                                                                                        ");
                document.add(para1);
                document.add(para1);

                String imageFile = "C:\\Users\\TRAIN 76\\Downloads\\logo.png";
                ImageData data = null;
                try {
                    data = ImageDataFactory.create(imageFile);
                } catch (MalformedURLException ex) {
                    System.out.println("Error : " + ex.getMessage());
                }
                Image img = new Image(data);
                img.setFixedPosition(38, 760);
                img.setHeight(60F);
                img.setWidth(60F);
                document.add(img);

                float[] pointColumnWidths = {80F, 80F, 80F, 80F, 80F, 80F};
                Table table = new Table(pointColumnWidths);
                table.addHeaderCell("Name");
                table.addHeaderCell("Surname");
                table.addHeaderCell("ID Number");
                table.addHeaderCell("BookId");
                table.addHeaderCell("ISBNo");
                table.addHeaderCell("Book Title");

                for (Member mm : members) {
                    table.addCell(mm.getName());
                    table.addCell(mm.getSurname());
                    table.addCell(mm.getIdNumber());
                    for (Book b : books) {
                        table.addCell(String.valueOf(b.getBookid()));
                        table.addCell(b.getISBNo());
                        table.addCell(b.getTitle());
                    }
                }

                document.add(table);

                document.close();
                System.out.println("Pdf created");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReportDAOImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return document;
    }

    @Override
    public List<Member> memberList() {
        List<Member> members = new ArrayList<>();

        try {
            con = dbm.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM member");
            while (rs.next()) {
                Member m = new Member();
                m.setTitle(rs.getString("Title"));
                m.setAddress(rs.getString("Address"));
                m.setCellNumber(rs.getString("CellNumber"));
                m.setEmail(rs.getString("Email"));
                m.setIdNumber(rs.getString("IDNo"));
                m.setName(rs.getString("Name"));
                m.setSurname(rs.getString("Surname"));
                m.setTelNumber(rs.getString("TelNumber"));
                try {
                    con2 = dbm.getConnection();
                    ps2 = con2.prepareStatement("SELECT role FROM login WHERE IDNo = ?");
                    ps2.setString(1, rs.getString("IDNo"));
                    rs2 = ps2.executeQuery();
                    if (rs2.next()) {
                        m.setRole(rs2.getInt("role"));
                    }
                } catch (SQLException ex) {
                    System.out.println("READING CODE ERROR :" + ex.getMessage());
                } finally {
                    if (rs2 != null) {
                        try {
                            rs2.close();
                        } catch (SQLException ex) {
                            System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                        }
                        try {
                            ps2.close();
                        } catch (SQLException ex) {
                            System.out.println("ResultSet closing failed error" + ex.getMessage());
                        }
                        try {
                            con2.close();
                        } catch (SQLException ex) {
                            System.out.println("ResultSet closing failed error" + ex.getMessage());
                        }
                    }
                }

                members.add(m);
            }

        } catch (SQLException ex) {
            System.out.println("READING CODE ERROR :" + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                }
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("ResultSet closing failed error" + ex.getMessage());
                }
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("ResultSet closing failed error" + ex.getMessage());
                }
            }
        }
        return members;
    }

    @Override
    public Document booksReserved(boolean isAvailable) {

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("C:\\Users\\TRAIN 76\\Desktop\\TechnoLib\\Location.properties"));
        } catch (IOException ex) {
            System.out.println("Error loading : " + ex.getMessage());
        }
        String dest = null;

        dest = prop.getProperty("reservedbooksreport");

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(dest);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportDAOImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);

        Paragraph paragraph = null;

        try {
            paragraph = new Paragraph(" RESERVED BOOKS REPORT ").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))//(StandardFonts.HELVETICA))
                    .setFontSize(30F)
                    .setUnderline()
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
        } catch (IOException ex) {
            System.out.println("Error :" + ex.getMessage());
        }

        document.add(paragraph);

        Paragraph para = new Paragraph(new java.util.Date().toString())
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12F);
        document.add(para);

        Paragraph para1 = new Paragraph("                                                                                        ");
        document.add(para1);
        document.add(para1);

        String imageFile = "C:\\Users\\TRAIN 76\\Downloads\\logo.png";
        ImageData data = null;
        try {
            data = ImageDataFactory.create(imageFile);
        } catch (MalformedURLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        Image img = new Image(data);
        img.setFixedPosition(38, 760);
        img.setHeight(60F);
        img.setWidth(60F);
        document.add(img);

        float[] pointColumnWidths = {80F, 80F, 80F, 80F, 80F, 80F, 80F};
        Table table = new Table(pointColumnWidths);
        table.addHeaderCell("ID Number");
        table.addHeaderCell("Name");
        table.addHeaderCell("Surname");
        table.addHeaderCell("BookId");
        table.addHeaderCell("ISBNo");
        table.addHeaderCell("Book Title");
        table.addHeaderCell("ReserveDate");

        List<String> list = new ArrayList<>();
        if (dbm != null) {
            try {
                con = dbm.getConnection();
                ps = con.prepareStatement("SELECT * FROM reservedbooks WHERE IsAvailable = ?");
                ps.setBoolean(1, isAvailable);
                rs = ps.executeQuery();
                while (rs.next()) {
                    try {
                        con2 = dbm.getConnection();
                        ps2 = con2.prepareStatement("SELECT Name, Surname FROM member WHERE IDNo = ?");
                        ps2.setString(1, rs.getString("memberid"));
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            table.addCell(rs.getString("memberid"));
                            table.addCell(rs2.getString("Name"));
                            table.addCell(rs2.getString("Surname"));
                        }

                    } catch (SQLException ex) {
                        System.out.println("READING CODE ERROR :" + ex.getMessage());
                    } finally {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (SQLException ex) {
                                System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                            }
                            try {
                                ps.close();
                            } catch (SQLException ex) {
                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                            }
                            try {
                                con.close();
                            } catch (SQLException ex) {
                                System.out.println("ResultSet closing failed error" + ex.getMessage());
                            }
                        }
                    }

                    table.addCell(String.valueOf(rs.getInt("bookid")));
                    table.addCell(rs.getString("ISBNo"));
                    table.addCell(rs.getString("Title"));
                    table.addCell(rs.getString("DateReserved"));

                }
                document.add(table);

                document.close();
                System.out.println("Pdf created");
            } catch (SQLException ex) {
                System.out.println("READING CODE ERROR :" + ex.getMessage());
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Prepaired Statement closing failed error" + ex.getMessage());
                    }
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                    }
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("ResultSet closing failed error" + ex.getMessage());
                    }
                }
            }
        }
        return document;
    }

}
