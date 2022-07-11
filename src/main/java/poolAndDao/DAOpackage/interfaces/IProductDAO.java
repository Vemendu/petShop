package poolAndDao.DAOpackage.interfaces;

import entities.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IProductDAO extends DAO {
    void createProduct(Product product) throws SQLException;
    void linkProductToCategory(long id, long category_id) throws SQLException;
    Product getProductById(long id) throws SQLException, IOException;
    List<Product> getProductsByCategory(long id) throws SQLException, IOException;
    List<Product> getAllProducts() throws SQLException, IOException;
    void changeName(String name, long id) throws SQLException;
    void changeDescription(String description, long id) throws SQLException;
    void changeCost(String cost, long id) throws SQLException;
    void changeInStock(int inStock, long id) throws SQLException;
    void changeSupplierId(long supplierId, long id) throws SQLException;
    void changeCategoryId(long categoryId, long id) throws SQLException;
}
