/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.dao;

import com.itextpdf.layout.Document;
import java.sql.Date;
import java.util.List;
import za.co.material.Book;
import za.co.material.Member;

/**
 *
 * @author TRAIN 76
 */
public interface ReportDAO {

    public List<Book> booksLoaned(List<Date> dateLoaned);

    public Document overdueLoans(List<Date> dueDate, String category);

    public List<Member> memberList();

    public Document booksReserved(boolean isAvailable);

}
