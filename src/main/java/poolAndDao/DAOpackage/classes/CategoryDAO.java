package poolAndDao.DAOpackage.classes;

import poolAndDao.ConnectionPool;
import poolAndDao.DAOpackage.interfaces.ICategoryDAO;
import entities.Category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    private ConnectionPool connectionPool;
    private Connection connection;
    private static final String ADD_CATEGORY = "INSERT INTO category (name) " +
            "VALUES (?)";
    private static final String ADD_CATEGORY_WITH_PARENT_ID = "INSERT INTO category (parent_id, name) " +
            "VALUES (?, ?)";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE (id=?)";
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
    private static final String UPDATE_NAME = "UPDATE category SET name=? WHERE id=?";
    private static final String UPDATE_PARENT_ID = "UPDATE category SET parent_id=? WHERE id=?";
    private static final String DELETE_CATEGORY = "DELETE  from category WHERE id=?";
    @Override
    public void createCategory(Category category) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_CATEGORY);
            statement.setString(1, category.getName());
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void createCategoryWithParent(Category category) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_CATEGORY_WITH_PARENT_ID);
            statement.setInt(1, category.getParentId());
            statement.setString(2, category.getName());
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Category getCategoryById(int id) throws SQLException, IOException {
        Category category = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                category = new Category(resultSet.getInt("parent_id"), resultSet.getString("name"));
                category.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() throws SQLException, IOException {
        List<Category> categories = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getString("name"));
                try{
                    category.setParentId(resultSet.getInt("parent_id"));
                    category.setId(resultSet.getInt("id"));
                    categories.add(category);

                }
                catch (Exception e)
                {
                    category.setId(resultSet.getInt("id"));
                    categories.add(category);
                }
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return categories;
    }

    @Override
    public void changeName(String name, int id) throws SQLException {
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
    public void changeParentId(int parentId, int id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_PARENT_ID);
            statement.setInt(1, parentId);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteCategory(int id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY);;
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
