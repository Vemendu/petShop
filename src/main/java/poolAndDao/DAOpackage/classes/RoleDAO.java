package poolAndDao.DAOpackage.classes;

import poolAndDao.ConnectionPool;
import poolAndDao.DAOpackage.interfaces.IRoleDAO;
import entities.Role;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RoleDAO implements IRoleDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_ROLE = "INSERT INTO role (name) " +
            "VALUES (?)";
    private static final String GET_ROLE_BY_ID = "SELECT * FROM role WHERE (role_id=?)";
    private static final String SELECT_ALL_ROLES = "SELECT * FROM role";
    public static final String UPDATE_ROLE = "UPDATE role SET name=? where role_id=?";
    private static final String DELETE_ROLE = "DELETE FROM role WHERE role_id=?";
    @Override
    public void createRole(Role role) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(ADD_ROLE);
            statement.setString(1, role.getName());
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Role getRoleById(long id) throws SQLException, IOException {
        Role role = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(GET_ROLE_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role = new Role(resultSet.getString("name"));
                role.setId(resultSet.getInt("id"));
            }
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() throws SQLException, IOException {
        List<Role> roles = new LinkedList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ROLES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role(resultSet.getString("name"));
                role.setId(resultSet.getInt("id"));
                roles.add(role);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return roles;
    }

    @Override
    public void updateRole(String role_name, long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_ROLE);
            statement.setString(1, role_name);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteRole(long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_ROLE);;
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
