/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.process.impl;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Book;
import za.co.material.Member;
import za.co.material.service.ReportService;
import za.co.material.service.impl.ReportServiceImpl;
import static za.co.material.service.impl.ReportServiceImpl.dateRangeList;
import za.co.process.ProcessInt;

/**
 *
 * @author thiof
 */
public class ReportProcess implements ProcessInt {

    private BasicDataSource dbmanager;
    RequestDispatcher view;

    public static final String ITALIC = "src/main/resources/fonts/Cardo-Italic.ttf";

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            dbmanager = (BasicDataSource) sc.getAttribute("dbman");
        }
        if (dbmanager == null) {
            return;
        }
        ReportService reportdao = new ReportServiceImpl(dbmanager);
        List<Date> range = new ArrayList<>();
        Properties prop = new Properties();
        String action = request.getParameter("action");
        switch (action.toLowerCase()) {
            case "getbooksloaned":
                           try {
                range = dateRangeList(Date.valueOf(request.getParameter("startDate")).toLocalDate(), Date.valueOf(request.getParameter("endDate")).toLocalDate());
            } catch (Exception ex) {
                System.out.println("Date error : " + ex.getMessage());
            }

            List<Book> list = reportdao.booksLoaned(range);

             {

                try {
                    prop.load(new FileReader("C:\\Users\\TRAIN 76\\Desktop\\TechnoLib\\Location.properties"));

                    String dest = prop.getProperty("loansreport");
                    PdfWriter writer = new PdfWriter(dest);
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    pdfDoc.addNewPage();
                    Document document = new Document(pdfDoc);

                    Paragraph paragraph = null;

                    try {
                        paragraph = new Paragraph(" LOANS REPORT ").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))//(StandardFonts.HELVETICA))
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

                    float[] pointColumnWidths = {110F, 110F, 110F, 110F};
                    Table table = new Table(pointColumnWidths);
                    table.addHeaderCell("ISBNo");
                    table.addHeaderCell("BookId");
                    table.addHeaderCell("Title");
                    table.addHeaderCell("Category");
                    for (Book b : list) {
                        table.addCell(b.getISBNo());
                        table.addCell(String.valueOf(b.getBookid()));
                        table.addCell(b.getTitle());
                        table.addCell(b.getCategory());

                    }

                    document.add(table);
                    AreaBreak aB = new AreaBreak();
                    document.add(aB);

                    document.close();
                    System.out.println("Pdf created");
                } catch (IOException ex) {
                    System.out.println("Error loading : " + ex.getMessage());
                }
            }
            if (list != null) {
                request.setAttribute("report", "LoansReport.pdf");
                request.setAttribute("msg", "Here's a list of loaned books : ");
            } else {
                request.setAttribute("booklist", null);
                request.setAttribute("msg", "the book has NOT BEEN found. Error");
            }
            view = request.getRequestDispatcher("ReportTable.jsp");
            break;

            case "getoverduebooks":
                String category = null;
                try {
                    category = request.getParameter("desc");
                    range = dateRangeList(Date.valueOf(request.getParameter("startDate")).toLocalDate(), Date.valueOf(request.getParameter("endDate")).toLocalDate());
                } catch (Exception ex) {
                    System.out.println("Date error : " + ex.getMessage());
                }
                Document overdue = reportdao.overdueLoans(range, category);

                if (overdue != null) {
                    request.setAttribute("report", "OverdueReport.pdf");
                    request.setAttribute("msg", "Here's a list of overdue books : ");
                } else {
                    request.setAttribute("report", null);
                    request.setAttribute("msg", " Error in report process ");
                }
                view = request.getRequestDispatcher("ReportTable.jsp");

                break;

            case "getmemberlist":

                List<Member> members = reportdao.memberList();
                try {
                    try {
                        prop.load(new FileReader("C:\\Users\\TRAIN 76\\Desktop\\TechnoLib\\Location.properties"));
                    } catch (IOException ex) {
                        System.out.println("Error loading : " + ex.getMessage());
                    }

                    String dest = prop.getProperty("membersreport");
                    PdfWriter writer = new PdfWriter(dest);

                    PdfDocument pdfDoc = new PdfDocument(writer);
                    pdfDoc.addNewPage();
                    Document document = new Document(pdfDoc);

                    Paragraph paragraph = null;

                    try {
                        paragraph = new Paragraph(" MEMBERS REPORT ").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))//(StandardFonts.HELVETICA))
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

                    float[] pointColumnWidths = {150F, 150F, 150F};
                    Table table = new Table(pointColumnWidths);
                    table.addHeaderCell("Name").setTextAlignment(TextAlignment.CENTER);
                    table.addHeaderCell("Surname").setTextAlignment(TextAlignment.CENTER);
                    table.addHeaderCell("ID Number").setTextAlignment(TextAlignment.CENTER);
                    for (Member m : members) {
                        table.addCell(m.getName());
                        table.addCell(m.getSurname());
                        table.addCell(m.getIdNumber());
                    }

                    document.add(table);

                    document.close();
                    System.out.println("Pdf created");
                } catch (FileNotFoundException ex) {
                    System.out.println("Creating File ERROR :" + ex.getMessage());
                }
                if (members != null) {
                    request.setAttribute("msg", "Here's the member");
                    request.setAttribute("report", "MembersReport.pdf");
                } else {
                    request.setAttribute("msg", " Error in report process");
                    request.setAttribute("memberlist", null);
                }

                view = request.getRequestDispatcher("ReportTable.jsp");
                break;

            case "getreservedbooks":
                boolean isAvailable = Boolean.valueOf(request.getParameter("isavailable"));

                Document reservedbooks = reportdao.booksReserved(isAvailable);

                if (reservedbooks != null) {
                    request.setAttribute("report", "ReservedReport.pdf");
                    request.setAttribute("msg", "Here's a list of overdue books : ");
                } else {
                    request.setAttribute("report", null);
                    request.setAttribute("msg", " Error in report process ");
                }
                view = request.getRequestDispatcher("ReportTable.jsp");

                break;

            default:
        }
        processTheResponse(request, response);
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        if (view != null) {
            try {
                view.forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}
