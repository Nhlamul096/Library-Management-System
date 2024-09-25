/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material;

import java.util.Objects;

/**
 *
 * @author Lungh
 */
public class Author {
    private int aid;
    private String author;

    public Author(int aid, String author) {
        this.aid = aid;
        this.author = author;
    }

    public Author() {
    }

    /**
     * @return the aid
     */
    public int getAid() {
        return aid;
    }

    /**
     * @param aid the aid to set
     */
    public void setAid(int aid) {
        this.aid = aid;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Author{" + "aid=" + aid + ", author=" + author + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.aid;
        hash = 67 * hash + Objects.hashCode(this.author);
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
        final Author other = (Author) obj;
        if (this.aid != other.aid) {
            return false;
        }
        return true;
    }
    
    
    
}
