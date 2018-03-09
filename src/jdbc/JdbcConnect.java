package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class JdbcConnect {
    private static JdbcConnect instance;
    private static String url;
    private static Properties jdbcProp;
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/BARBER";
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Используем один коннект

    public static JdbcConnect getInstance() {
        if (instance == null) {
            instance = new JdbcConnect();
        }
        return instance;
    }

    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
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
}
