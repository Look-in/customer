package dao.request;

import entity.ItemType;
import jdbc.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectItemTypeDao {

    private static SelectItemTypeDao instance;

    public static SelectItemTypeDao getInstance() {
        if (instance == null) {
            instance = new SelectItemTypeDao();
        }
        return instance;
    }

    public List<ItemType> readItemType() {
        final String sql = "SELECT ID,ITEM_TYPE " +
                "FROM ITEM_TYPE;";
        List<ItemType> type = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                type.add(new ItemType(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
        return type;
    }

    private static PreparedStatement selectPreparedStatement(Connection connection, int id) throws SQLException {
        final String SQL = "SELECT " +
                "ITEM_TYPE " +
                "FROM ITEM_TYPE WHERE id= ?;";
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, id);
        return statement;
    }

    public String readItemType(int itemStatusId) {
        String result = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = selectPreparedStatement(connection, itemStatusId);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "Error reading ItemStatuses:" + exc.getMessage());
        }
        return result;
    }

}