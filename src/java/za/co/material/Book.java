package za.co.material;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lungh
 */
public class Book {

    private String ISBNo;
    private String title;
    private String category;
    private Date pubDate;
    private String rackNo;
    private boolean isAvailable;
    private int bookid;
    private Author authors;

    public Book(String ISBNo, String title, String category, Date pubDate, String rackNo, boolean isAvailable, int bookid, Author authors) {
        this.ISBNo = ISBNo;
        this.title = title;
        this.category = category;
        this.pubDate = pubDate;
        this.rackNo = rackNo;
        this.isAvailable = isAvailable;
        this.bookid = bookid;
        this.authors = authors;
    }

    public Book() {
    }

    /**
     * @return the ISBNo
     */
    public String getISBNo() {
        return ISBNo;
    }

    /**
     * @param ISBNo the ISBNo to set
     */
    public void setISBNo(String ISBNo) {
        this.ISBNo = ISBNo;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getCategory() {
        return category;
    }

    /**
     * @param cid the cid to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the pubDate
     */
    public Date getPubDate() {
        return pubDate;
    }

    /**
     * @param pubDate the pubDate to set
     */
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * @return the rackNo
     */
    public String getRackNo() {
        return rackNo;
    }

    /**
     * @param rackNo the rackNo to set
     */
    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    /**
     * @return the isAvailable
     */
    public boolean getIsIsAvailable() {
        return isAvailable;
    }

    /**
     * @param isAvailable the isAvailable to set
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * @return the bookid
     */
    public int getBookid() {
        return bookid;
    }

    /**
     * @param bookid the bookid to set
     */
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    /**
     * @return the authors
     */
    public Author getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(Author authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" + "ISBNo=" + ISBNo + ", title=" + title + ", category=" + category + ", pubDate=" + pubDate + ", rackNo=" + rackNo + ", isAvailable=" + isAvailable + ", bookid=" + bookid + ", authors=" + authors + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.ISBNo);
        hash = 79 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.ISBNo, other.ISBNo)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

}
