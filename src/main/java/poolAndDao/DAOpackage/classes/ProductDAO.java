package poolAndDao.DAOpackage.classes;

import poolAndDao.ConnectionPool;
import poolAndDao.DAOpackage.interfaces.IProductDAO;
import entities.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final String ADD_PRODUCT = "INSERT INTO product (name, description, cost, in_stock, supplier_id, category_id) " +
            "VALUES (?,?,?,?,?,?)";
    private  static final String LINK_PRODUCT_TO_CATEGORY = "INSERT INTO product_category (product_id, category_id)" +
            "VALUES (?,?)";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id=?";
    private static final String GET_PRODUCTS_BY_CATEGORY = "SELECT * FROM product WHERE category_id=?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String UPDATE_NAME = "UPDATE product SET name=? WHERE id=?";
    private static final String UPDATE_DESCRIPTION = "UPDATE product SET description=? WHERE id=?";
    private static final String UPDATE_COST = "UPDATE product SET cost=? WHERE id=?";
    private static final String UPDATE_IN_STOCK = "UPDATE product SET in_stock =? WHERE id=?";
    private static final String UPDATE_SUPPLIER = "UPDATE product SET supplier_id=? WHERE id=?";
    private static final String UPDATE_CATEGORY = "UPDATE product SET category_id=? WHERE id=?";
    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public void createProduct(Product product) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getCost());
            statement.setInt(4, product.getInStock());
            statement.setLong(5, product.getSupplierId());
            statement.setLong(6, product.getCategoryId());
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void linkProductToCategory(long id, long category_id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(LINK_PRODUCT_TO_CATEGORY);
            statement.setLong(1, id);
            statement.setLong(2, category_id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public Product getProductById(long id) throws SQLException, IOException {
        Product product = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = new Product(resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("cost"), resultSet.getInt("in_stock"), resultSet.getLong("supplier_id"), resultSet.getLong("category_id"));
                product.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(long category_id) throws SQLException, IOException {
        List<Product> products = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY);
            statement.setLong(1, category_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("cost"), resultSet.getInt("in_stock"), resultSet.getLong("supplier_id"), resultSet.getLong("category_id"));
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return products;
    }
    @Override
    public List<Product> getAllProducts() throws SQLException, IOException {
        List<Product> products = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("cost"), resultSet.getInt("in_stock"), resultSet.getLong("supplier_id"), resultSet.getLong("category_id"));
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return products;
    }

    @Override
    public void changeName(String name, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_NAME);
            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeDescription(String description, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_DESCRIPTION);
            statement.setString(1, description);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeCost(String cost, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_COST);
            statement.setString(1, cost);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeInStock(int inStock, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_IN_STOCK);
            statement.setInt(1, inStock);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeSupplierId(long supplierId, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_SUPPLIER);
            statement.setLong(1, supplierId);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeCategoryId(long categoryId, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY);
            statement.setLong(1, categoryId);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
