package dao.request;

import entity.ItemStatus;
import jdbc.JdbcConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectItemStatusDao {
    private static SelectItemStatusDao instance;

    public static SelectItemStatusDao getInstance() {
        if (instance == null) {
            instance = new SelectItemStatusDao();
        }
        return instance;
    }

    public List<ItemStatus> readItemStatus() {
        final String SQL = "SELECT " +
                "ID,STATUS " +
                "FROM ITEM_STATUS order by id;";
        List<ItemStatus> status = new ArrayList<>();
        Connection connection = JdbcConnect.getInstance().connect();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            while (rs.next()) {
                status.add(new ItemStatus(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "Error reading ItemStatuses:" + exc.getMessage());
        }
        return status;
    }

    private static PreparedStatement selectPreparedStatement(int id) throws SQLException {
        final String SQL = "SELECT " +
                "STATUS " +
                "FROM ITEM_STATUS WHERE id= ?;";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, id);
        return statement;
    }

    public String readItemStatus(int itemStatusId) {
        String result=null;
        try (PreparedStatement statement = selectPreparedStatement(itemStatusId);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                result=rs.getString(1);
            }
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "Error reading ItemStatuses:" + exc.getMessage());
        }
        return result;
    }

}
