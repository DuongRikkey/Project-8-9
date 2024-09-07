package projectmd2.business.feature.categoryimpl;

import projectmd2.business.design.ICategory;
import projectmd2.business.entity.Category;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;

import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements ICategory {
    private static List<Category> categoryList;

    public CategoryImpl() {
        categoryList = IOFile.readFromFile(ShopConstant.CATEGORY_PATH);
    }

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public Category findById(Integer id) {
        return categoryList.stream().filter(c->c.getCategoryId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Category category) {
        if(findById(category.getCategoryId()) == null) {
            categoryList.add(category);
        }else {
            categoryList.set(categoryList.indexOf(findById(category.getCategoryId())), category);
        }
        IOFile.writeToFile(ShopConstant.CATEGORY_PATH, categoryList);
    }

    @Override
    public void deleteById(Integer id) {
        categoryList.removeIf(c->c.getCategoryId() == id);
        IOFile.writeToFile(ShopConstant.CATEGORY_PATH, categoryList);
    }
}
