package za.co.material.service.impl;

import com.itextpdf.layout.Document;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Book;
import za.co.material.Member;
import za.co.material.dao.ReportDAO;
import za.co.material.dao.impl.ReportDAOImpl;
import za.co.material.service.ReportService;

public class ReportServiceImpl implements ReportService {

    private ReportDAO reportdao;

    public ReportServiceImpl(BasicDataSource dbm) {
        reportdao = new ReportDAOImpl(dbm);
    }
    
    

    @Override
    public List<Book> booksLoaned(List<Date> dateLoaned) {
        if (dateLoaned != null && dateLoaned.size() != 0) {
            return reportdao.booksLoaned(dateLoaned);
        }
        return null;
    }

    @Override
    public Document overdueLoans(List<Date> dueDate, String category) {
        return reportdao.overdueLoans(dueDate, category);
    }

    @Override
    public List<Member> memberList() {
        return reportdao.memberList();
    }

    @Override
    public Document booksReserved(boolean isAvailable) {
        return reportdao.booksReserved(isAvailable);
    }

    private static Calendar getCalendarWithoutTime(LocalDate date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        return calendar;
    }

    public static List dateRangeList(LocalDate startDate, LocalDate endDate) {
        List<Date> datesInRange = new ArrayList<>();
        
        Calendar calendar = getCalendarWithoutTime(startDate);
        Calendar endCalendar = getCalendarWithoutTime(endDate);

        while (calendar.before(endCalendar)) {
            java.sql.Date result = Date.valueOf(LocalDate.of(calendar.getTime().getYear() + 1900, calendar.getTime().getMonth(), calendar.getTime().getDate()));
            datesInRange.add(result);
            startDate.plusDays(1); 
            calendar.add(Calendar.DATE, 1);
        }

        return datesInRange;
    }

}
