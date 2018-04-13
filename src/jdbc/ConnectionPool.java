package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static ConnectionPool instance;

    private final String URL = "jdbc:mysql://localhost:3306/BARBER";

    private static Properties jdbcProp;

    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(20);

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(20);
        }
        return instance;
    }

    private ConnectionPool(int poolSize) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        for (int i = 0; i < poolSize; i++) {
            connections.add(new PooledConnection(DriverManager.getConnection(URL, getProperties())));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Properties getProperties() {
        if (jdbcProp == null) {
            jdbcProp = new Properties();
            jdbcProp.put("user", "root");
            jdbcProp.put("password", "sql");
            jdbcProp.put("autoReconnect", "true");
            jdbcProp.put("characterEncoding", "UTF-8");
            jdbcProp.put("useUnicode", "true");
        }
        return jdbcProp;
    }

    public Connection getConnection() {
        return connections.poll();
    }

    public void putConnection(Connection connection) {
        connections.add(connection);
    }
}
