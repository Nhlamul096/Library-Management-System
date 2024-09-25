package za.co.material.service.impl;

import java.util.List;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Category;
import za.co.material.dao.CategoryDAO;
import za.co.material.dao.impl.CategoryDAOImpl;
import za.co.material.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categorydao;

    public CategoryServiceImpl(BasicDataSource dbm) {
        categorydao = new CategoryDAOImpl(dbm);
    }

    @Override
    public boolean add(String category) {
        if (category != null && !category.isEmpty()) {
            return categorydao.add(category);
        }
        return false;
    }

    @Override
    public boolean delete(Category category) {
        return category != null ? categorydao.delete(category) : false;
    }

    @Override
    public boolean update(Category category) {
        return category != null ? categorydao.update(category) : false;
    }

    @Override
    public Category get(int categoryId) {
        if( categoryId <= 0){
            return null;
        }
        return categorydao.get(categoryId);
    }

    @Override
    public List<Category> getAll(boolean isActive) {
        return categorydao.getAll(isActive);
    }

    @Override
    public boolean setCategoryActivity(int cid, boolean isActive) {
        if(cid <= 0){
            return false;
        }
        return categorydao.setCategoryActivity(cid, isActive);
    }

}
