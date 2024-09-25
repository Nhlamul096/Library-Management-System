package za.co.material.service;

import com.itextpdf.layout.Document;
import java.sql.Date;
import java.util.List;
import za.co.material.Book;
import za.co.material.Member;
import za.co.process.ProcessInt;

public interface ReportService {

    public List<Book> booksLoaned(List<Date> dateLoaned);

    public Document overdueLoans(List<Date> dueDate, String category);

    public List<Member> memberList();

    public Document booksReserved(boolean isAvailable);
    
}
