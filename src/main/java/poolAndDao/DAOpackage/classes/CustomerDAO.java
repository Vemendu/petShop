package poolAndDao.DAOpackage.classes;

import poolAndDao.ConnectionPool;
import poolAndDao.DAOpackage.interfaces.ICustomerDAO;
import entities.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    private static final String ADD_CUSTOMER = "INSERT INTO customer (first_name, last_name, birthday_date, phone_number, address, language, country, role_id, username, password, email) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE (id=?)";
    private static final String GET_CUSTOMER_BY_USERNAME = "SELECT * FROM customer WHERE username=?";
    private static final String GET_CUSTOMER_BY_LOGIN = "SELECT * FROM customer WHERE username=? and password=?";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
    private static final String UPDATE_PASSWORD = "UPDATE customer SET password=? WHERE id=?";
    private static final String UPDATE_PHONE_NUMBER = "UPDATE customer SET phone_number=? WHERE id=?";
    private static final String UPDATE_ADDRESS = "UPDATE customer SET address=? WHERE id=?";
    private static final String UPDATE_LANGUAGE = "UPDATE customer SET language =? WHERE id=?";
    private static final String UPDATE_COUNTRY = "UPDATE customer SET country=? WHERE id=?";
    private static final String UPDATE_ROLE = "UPDATE customer SET role_id=? WHERE id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE id=?";

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public void createCustomer(Customer customer) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_CUSTOMER);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setDate(3, customer.getBirthdayDate());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getAddress());
            statement.setString(6, customer.getLanguage());
            statement.setString(7, customer.getCountry());
            statement.setInt(8, customer.getRoleId());
            statement.setString(9, customer.getUsername());
            statement.setString(10, customer.getPassword());
            statement.setString(11, customer.getEmail());
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Customer getCustomerById(long id) throws SQLException, IOException {
        Customer customer = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getDate("birthday_date"), resultSet.getString("phone_number"), resultSet.getString("address"), resultSet.getString("language"), resultSet.getString("country"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"));
                customer.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return customer;
    }

    @Override
    public Customer getCustomerByUsername(String username) throws SQLException, IOException {
        Customer customer = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getDate("birthday_date"), resultSet.getString("phone_number"), resultSet.getString("address"), resultSet.getString("language"), resultSet.getString("country"), username, resultSet.getString("password"), resultSet.getString("email"));
                customer.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return customer;
    }

    @Override
    public Customer getCustomerByLogin(String username, String password) throws SQLException, IOException {
        Customer customer = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_BY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getDate("birthday_date"), resultSet.getString("phone_number"), resultSet.getString("address"), resultSet.getString("language"), resultSet.getString("country"), username, password, resultSet.getString("email"));
                customer.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException, IOException {
        List<Customer> customers = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getDate("birthday_date"), resultSet.getString("phone_number"), resultSet.getString("address"), resultSet.getString("language"), resultSet.getString("country"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"));
                customer.setId(resultSet.getInt("id"));
                customers.add(customer);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return customers;
    }

    @Override
    public void changePassword(String password, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD);
            statement.setString(1, password);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changePhoneNumber(String phoneNumber, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_PHONE_NUMBER);
            statement.setString(1, phoneNumber);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeAddress(String address, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_ADDRESS);
            statement.setString(1, address);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeLanguage(String language, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_LANGUAGE);
            statement.setString(1, language);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeCountry(String country, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_COUNTRY);
            statement.setString(1, country);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeRole(int role_id, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_ROLE);
            statement.setInt(1, role_id);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteCustomer(long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);;
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
