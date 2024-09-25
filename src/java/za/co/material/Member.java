
package za.co.material;

import java.util.Objects;

/**
 *
 * @author TRAIN 76
 */
public class Member {
    private String idNumber;
    private String name;
    private String surname;
    private String title;
    private String address;
    private String telNumber;
    private String cellNumber;
    private String email;
    private int role;

    public Member() {
    }
    
    public Member(String idNumber, String name, String surname, String title, String address, String telNumber, String cellNumber, String email, int role) {
        this.idNumber = idNumber;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.address = address;
        this.telNumber = telNumber;
        this.cellNumber = cellNumber;
        this.email = email;
        this.role = role;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }
   
    

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.surname);
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
        final Member other = (Member) obj;
        if (!Objects.equals(this.idNumber, other.idNumber)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Member{" + "idNumber=" + idNumber + ", name=" + name + ", surname=" + surname + ", title=" + title + ", address=" + address + ", telNumber=" + telNumber + ", cellNumber=" + cellNumber + ", email=" + email + ", role=" + role + '}';
    }

  
    
    
    
    
    
}
