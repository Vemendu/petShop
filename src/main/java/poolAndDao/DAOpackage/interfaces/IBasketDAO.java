package poolAndDao.DAOpackage.interfaces;

import entities.Basket;
import entities.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IBasketDAO extends DAO {
    void createBasket(long id) throws SQLException;
    void addProductToBasket(long id, long product_id) throws SQLException;
    Basket getBasketByCustomerId(long id) throws SQLException, IOException;
    List<Long> getProductsIdByBasketId(long basket_id) throws SQLException, IOException;
    void addSameProductToBasket(long id, long product_id) throws SQLException;
    void removeSameProductToBasket(long id, long product_id) throws SQLException;
    void deleteBasket(long id) throws SQLException;
    void deleteProductsInBasket(long id) throws SQLException;
    void deleteProductFromBasket(long id, long product_id) throws SQLException;
}
