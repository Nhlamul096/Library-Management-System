package za.co.material.service;

import java.util.List;
import za.co.material.Category;

public interface CategoryService {

    boolean add(String category);

    boolean delete(Category category);

    boolean update(Category category);

    Category get(int categoryId);

    List<Category> getAll(boolean isActive);

    boolean setCategoryActivity(int cid, boolean isActive);
}
