package dao.request;

import jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SelectTypeDao {

    private static SelectTypeDao instance;

    public static SelectTypeDao getInstance() {
        if (instance == null) {
            instance = new SelectTypeDao();
        }
        return instance;
    }


    public Map<Integer, String> readItemType() {
        Map<Integer, String> type = new HashMap<>();
        final String sql = "SELECT ID,ITEM_TYPE " +
                "FROM ITEM_TYPE;";
        Connection connection = JdbcConnect.getInstance().connect();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                type.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
        return type;
    }

}