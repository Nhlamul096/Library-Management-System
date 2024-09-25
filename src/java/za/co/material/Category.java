package za.co.material;

import java.util.Objects;

public class Category {

    private int cId;
    private String description;
    private boolean isActive;

    public Category(int cId, String description, boolean isActive) {
        this.cId = cId;
        this.description = description;
        this.isActive = isActive;
    }

    public Category(int cId, String description) {
        this(cId, description, true);
    }

    public Category() {
        this(0, "", false);
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.cId;
        hash = 11 * hash + Objects.hashCode(this.description);
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
        final Category other = (Category) obj;
        if (this.cId != other.cId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "cId=" + cId + ", description=" + description + ", isActive=" + isActive + '}';
    }

   

}
