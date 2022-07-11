package poolAndDao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private String user;
    private String password;
    private String driverName;
    private String url;

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private final ResourceBundle properties = ResourceBundle.getBundle("ConnectionPool");
    private final int POOL_SIZE = Integer.parseInt(properties.getString("db.poolSize"));
    private static volatile ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);

    private ConnectionPool() {
        init();
    }

    private void init() {
        setDataForConnection();
        loadDrivers();
        initPoolData();
    }

    private void setDataForConnection() {
        this.driverName = properties.getString("db.driver");
        this.url = properties.getString("db.url");
        this.user = properties.getString("db.user");
        this.password = properties.getString("db.password");
    }

    private void loadDrivers() {
        try {
            Driver driver = (Driver) Class.forName(driverName).newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    private void initPoolData() {
        Connection connection;

        while (connectionQueue.size() < POOL_SIZE) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                connectionQueue.put(connection);
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
                logger.debug("Bug");
            }
        }
    }

    public synchronized Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        if ((connection != null) && (connectionQueue.size() <= POOL_SIZE)) {
            try {
                connectionQueue.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
