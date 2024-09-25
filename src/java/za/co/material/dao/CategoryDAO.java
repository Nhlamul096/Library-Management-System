
package za.co.material.dao;

import java.util.List;
import za.co.material.Category;

public interface CategoryDAO {
    boolean add(String category);
    boolean delete(Category category);
    boolean update(Category category);
    Category get(int categoryId);
    List<Category> getAll(boolean isActive);
    boolean setCategoryActivity(int cid, boolean isActive);
}
