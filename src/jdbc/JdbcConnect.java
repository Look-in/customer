package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static java.sql.DriverManager.getConnection;

public class JdbcConnect {
    private static String url;
    private static Properties jdbcProp;

    public JdbcConnect(){

    }

    public static Connection connect() throws SQLException {
        url = "jdbc:mysql://localhost:3306/BARBER";
        jdbcProp = new Properties();
        jdbcProp.put("user", "root");
        jdbcProp.put("password", "sql");
        jdbcProp.put("autoReconnect", "true");
        jdbcProp.put("characterEncoding", "UTF-8");
        jdbcProp.put("useUnicode", "true");
       // Connection cn = null;
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return getConnection(url, jdbcProp);

    }


}
