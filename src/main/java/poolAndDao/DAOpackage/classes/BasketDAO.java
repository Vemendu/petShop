package poolAndDao.DAOpackage.classes;

import entities.Category;
import entities.Product;
import poolAndDao.ConnectionPool;
import poolAndDao.DAOpackage.interfaces.IBasketDAO;
import entities.Basket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BasketDAO implements IBasketDAO {
    private static final String ADD_BASKET = "INSERT INTO basket (customer_id) " +
            "VALUES (?)";
    private  static final String LINK_PRODUCT_TO_BASKET = "INSERT INTO basket_product (basket_ID, product_id)" +
            "VALUES (?,?)";
    private static final String GET_BASKET_BY_CUSTOMER_ID = "SELECT * FROM basket WHERE customer_id=?";
    private static final String GET_PRODUCTS_ID_BY_BASKET_ID = "SELECT product_id FROM basket_product WHERE basket_id=?";
    private static final String ADD_SAME_PRODUCT_TO_BASKET = "UPDATE basket_product set count = count + 1 WHERE basket_id=? and product_id=?";
    private static final String REMOVE_SAME_PRODUCT_FROM_BASKET = "UPDATE basket_product set count = count - 1 WHERE basket_id=? and product_id=?";
    private static final String DELETE_BASKET = "DELETE FROM basket WHERE customer_id=?";
    private static final String DELETE_PRODUCTS_IN_BASKET = "DELETE FROM basket_product WHERE basket_id=?";
    private static final String DELETE_PRODUCT_FROM_BASKET = "DELETE FROM basket_product WHERE basket_id=? and product_id=?";

    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public void createBasket(long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_BASKET);
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void addProductToBasket(long id, long product_id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(LINK_PRODUCT_TO_BASKET);
            statement.setLong(1, id);
            statement.setLong(2, product_id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Basket getBasketByCustomerId(long id) throws SQLException, IOException {
        Basket basket = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_BASKET_BY_CUSTOMER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                basket = new Basket(resultSet.getLong("customer_id"));
                basket.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return basket;
    }

    public List<Long> getProductsIdByBasketId(long basket_id) throws SQLException
    {
        List<Long> productIds = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_ID_BY_BASKET_ID);
            statement.setLong(1, basket_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productIds.add(resultSet.getLong("product_id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return productIds;
    }

    @Override
    public void addSameProductToBasket(long id, long product_id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_SAME_PRODUCT_TO_BASKET);
            statement.setLong(1, id);
            statement.setLong(2, product_id);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void removeSameProductToBasket(long id, long product_id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(REMOVE_SAME_PRODUCT_FROM_BASKET);
            statement.setLong(1, id);
            statement.setLong(2, product_id);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteBasket(long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_BASKET);;
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteProductsInBasket(long id) throws SQLException{
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_IN_BASKET);;
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteProductFromBasket(long id, long product_id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_FROM_BASKET);;
            statement.setLong(1, id);
            statement.setLong(2, product_id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
