package dao.request;

import jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SelectItemStatusDao {
    private static SelectItemStatusDao instance;

    public static SelectItemStatusDao getInstance() {
        if (instance == null) {
            instance = new SelectItemStatusDao();
        }
        return instance;
    }

    public Map<Integer, String> readItemStatuses() {
        final String SQL = "SELECT " +
                "ID,STATUS " +
                "FROM ITEM_STATUS order by id;";
        Map<Integer, String> status = new HashMap<>();
        Connection connection = JdbcConnect.getInstance().connect();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            while (rs.next()) {
                status.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "Error reading ItemStatuses:" + exc.getMessage());
        }
        return status;

    }

}
