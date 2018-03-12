package dao.request;

import dao.SelectDao;
import entity.event.Item;
import jdbc.JdbcConnect;

import java.sql.*;
import java.util.ArrayList;


public class SelectDefaultItemDao implements SelectDao<Item> {

    private static SelectDefaultItemDao instance;

    public static SelectDefaultItemDao getInstance() {
        if (instance == null) {
            instance = new SelectDefaultItemDao();
        }
        return instance;
    }

    private static PreparedStatement selectAllPreparedStatement(int id) throws SQLException {
        final String SQL = "SELECT " +
                "ID,PRICE,NAME,DESCRIPTION,ITEM_STATUS_ID, ITEM_TYPE_ID " +
                "FROM ITEM WHERE ITEM_TYPE_ID=?;";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, id);
        return statement;
    }

    public ArrayList<Item> readListItem(int typeItemId) {
        ArrayList<Item> items = new ArrayList<>();
        try (PreparedStatement statement = selectAllPreparedStatement(typeItemId);
             ResultSet rs = statement.executeQuery()) {
            Item item;
            while (rs.next()) {
                item = new Item();
                setItemParameter(rs, item);
                items.add(item);
            }
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }

        return items;
    }

    private void setItemParameter(ResultSet rs, Item item) throws SQLException {
        item.setItemId(rs.getInt(1));
        item.setPrice(rs.getFloat(2));
        item.setName(rs.getString(3));
        item.setDescription(rs.getString(4));
        item.setItemStatus(rs.getInt(5));
        item.setItemType(rs.getInt(6));
        //Преобразуем Blob в строку формата base 64
                            /*Blob ph = rs.getBlob(7);
                            tmpItem.setImage(ph.getBytes(1, (int) ph.length()));*/
    }

    public ArrayList<Item> readListItem() {

        ArrayList<Item> items = new ArrayList<>();
        Connection connection = JdbcConnect.getInstance().connect();
        final String SQL = "SELECT " +
                "ID,PRICE,NAME,DESCRIPTION, ITEM_STATUS_ID,ITEM_TYPE_ID " +
                "FROM ITEM;";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            Item item;
            while (rs.next()) {
                item = new Item();
                setItemParameter(rs, item);
                items.add(item);
            }
        } catch (SQLException exc) {
            throw new RuntimeException(exc);
        }

        return items;
    }


    private PreparedStatement selectPreparedStatement(int id) throws SQLException {
        final String SQL = "SELECT " +
                "ID, PRICE, NAME, DESCRIPTION, ITEM_STATUS_ID, ITEM_TYPE_ID " +
                "FROM ITEM WHERE ITEM.ID=?;";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public void readItem(Item item) {
        try (PreparedStatement statement = selectPreparedStatement(item.getItemId());
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                setItemParameter(rs, item);
            }
        } catch (Exception exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
    }

}