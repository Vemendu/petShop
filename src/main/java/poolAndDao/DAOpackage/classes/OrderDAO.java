package poolAndDao.DAOpackage.classes;

import poolAndDao.ConnectionPool;
import poolAndDao.DAOpackage.interfaces.IOrderDAO;
import entities.Order;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static final String ADD_ORDER = "INSERT INTO \"order\" (customer_id, date_ordered, status, target_address, payment_choice, preferred_time, comment, total_price) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private  static final String LINK_PRODUCT_TO_ORDER = "INSERT INTO order_product (order_id, product_id)" +
            "VALUES (?,?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM \"order\" WHERE id=?";
    private static final String GET_ALL_ORDERS_BY_CUSTOMER_ID = "SELECT * FROM \"order\" WHERE customer_id=?";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM \"order\"";
    private static final String UPDATE_DATE_DELIVERED = "UPDATE \"order\" SET date_delivered=? WHERE id=?";
    private static final String UPDATE_STATUS = "UPDATE \"order\" SET status=? WHERE id=?";


    private ConnectionPool connectionPool;
    private Connection connection;
    @Override
    public void createOrder(Order order) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_ORDER);
            statement.setLong(1, order.getCustomerId());
            statement.setDate(2, order.getDateOrdered());
            statement.setString(3, order.getStatus());
            statement.setString(4, order.getTargetAddress());
            statement.setString(5, order.getPaymentChoice());
            statement.setDate(6, order.getPreferredTime());
            statement.setString(7, order.getComment());
            statement.setLong(8, order.getTotalPrice());
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void linkOrderAndProduct(long id, long product_id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(LINK_PRODUCT_TO_ORDER);
            statement.setLong(1, id);
            statement.setLong(2, product_id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Order getOrderById(long id) throws SQLException, IOException {
        Order order = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_ORDER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = new Order(resultSet.getLong("customer_id"), resultSet.getDate("date_ordered"), resultSet.getDate("date_delivered"), resultSet.getString("status"), resultSet.getString("target_address"), resultSet.getString("payment_choice"), resultSet.getDate("preferred_time"), resultSet.getString("comment"), resultSet.getInt("total_price"));
                order.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> getAllOrdersByCustomerId() throws SQLException, IOException {
        List<Order> orders = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_BY_CUSTOMER_ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong("customer_id"), resultSet.getDate("date_ordered"), resultSet.getDate("date_delivered"), resultSet.getString("status"), resultSet.getString("target_address"), resultSet.getString("payment_choice"), resultSet.getDate("preferred_time"), resultSet.getString("comment"), resultSet.getInt("total_price"));
                order.setId(resultSet.getInt("id"));
                orders.add(order);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException, IOException {
        List<Order> orders = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong("customer_id"), resultSet.getDate("date_ordered"), resultSet.getDate("date_delivered"), resultSet.getString("status"), resultSet.getString("target_address"), resultSet.getString("payment_choice"), resultSet.getDate("preferred_time"), resultSet.getString("comment"), resultSet.getInt("total_price"));
                order.setId(resultSet.getInt("id"));
                orders.add(order);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public void changeDateDelivered(Date date, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_DATE_DELIVERED);
            statement.setDate(1, date);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeStatus(String status, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_DATE_DELIVERED);
            statement.setString(1, status);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
