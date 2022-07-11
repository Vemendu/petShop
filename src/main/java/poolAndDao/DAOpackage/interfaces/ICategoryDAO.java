package poolAndDao.DAOpackage.interfaces;

import entities.Category;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ICategoryDAO extends DAO {
    void createCategory(Category category) throws SQLException;
    void createCategoryWithParent(Category category) throws SQLException;
    Category getCategoryById(int id) throws SQLException, IOException;
    List<Category> getAllCategories() throws SQLException, IOException;
    void changeName(String name, int id) throws SQLException;
    void changeParentId(int parentId, int id) throws SQLException;
    void deleteCategory(int id) throws SQLException;
}
