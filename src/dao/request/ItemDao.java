package dao.request;

import entity.event.Item;
import jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ItemDao {


    private PreparedStatement createPreparedStatement(Item entity) throws SQLException {
        final String sql = "INSERT INTO ITEM "
                + "(PRICE,NAME,DESCRIPTION,ITEM_STATUS_ID,ITEM_TYPE_ID) VALUES "
                + "(?, ?, ?, ?, ?)";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setFloat(1, entity.getPrice());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getDescription());
        statement.setInt(4, entity.getItemStatusId());
        statement.setInt(5, entity.getTypeId());
        return statement;
    }

    public void createItem(Item entity) {
        try (PreparedStatement statement = createPreparedStatement(entity)) {
            Connection connection = JdbcConnect.getInstance().connect();
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                entity.setItemId(keys.getInt(1));
            }
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "JDBC error:" + exc.getMessage());
        }
    }

    private PreparedStatement updatePreparedStatement(Connection connection, Item entity) throws SQLException {
        final String sql = "UPDATE ITEM SET "
                + "PRICE = ? , NAME = ? , DESCRIPTION= ? , ITEM_STATUS_ID = ? "
                + "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setFloat(1, entity.getPrice());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getDescription());
        statement.setInt(4, entity.getItemStatusId());
        statement.setInt(5, entity.getItemId());
        return statement;
    }

    public void updateItem(Item entity) {
        Connection connection = JdbcConnect.getInstance().connect();
        try (PreparedStatement statement = updatePreparedStatement(connection, entity)) {
            statement.executeUpdate();
        } catch (SQLException exc) {
            throw new RuntimeException(
                    "JDBC error:" + exc.getMessage());
        }

    }

    public void deleteItem(Integer id) {

    }
}
