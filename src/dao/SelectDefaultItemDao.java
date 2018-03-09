package dao;

import entity.event.Item;
import jdbc.JdbcConnect;

import java.sql.*;
import java.util.ArrayList;


public class SelectDefaultItemDao {

    private static SelectDefaultItemDao instance;

    public static SelectDefaultItemDao getInstance() {
        if (instance == null) {
            instance = new SelectDefaultItemDao();
        }
        return instance;
    }

    private static PreparedStatement selectAllPreparedStatement(int id) throws SQLException {
        final String SQL = "SELECT " +
                "item.ID,PRICE,NAME,DESCRIPTION,item_status.ID,STATUS" +
                " FROM ITEM INNER JOIN " +
                "item_status " +
                "ON item.ITEM_STATUS_ID=item_status.ID " +
                "WHERE ITEM_TYPE_ID=?;";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, id);
        return statement;
    }

    public ArrayList<Item> readFilteredListItem(int typeItemId) {
        ArrayList<Item> items = new ArrayList<>();
        try (PreparedStatement statement = selectAllPreparedStatement(typeItemId);
             ResultSet rs = statement.executeQuery()) {
            Item item;
            while (rs.next()) {
                item = new Item();
                setItemParameter(rs, item);
                item.setTypeId(typeItemId);
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
        item.setStatusId(rs.getInt(5));
        item.setItemStatus(rs.getString(6));
        //Преобразуем Blob в строку формата base 64
                            /*Blob ph = rs.getBlob(7);
                            tmpItem.setImage(ph.getBytes(1, (int) ph.length()));*/
    }

    public ArrayList<Item> readAllListItem() {

        ArrayList<Item> items = new ArrayList<>();
        Connection connection = JdbcConnect.getInstance().connect();
        final String SQL = "SELECT " +
                "item.ID,PRICE,NAME,DESCRIPTION,item_status.ID,STATUS,ITEM_TYPE_ID" +
                " FROM ITEM INNER JOIN " +
                "item_status " +
                "ON item.ITEM_STATUS_ID=item_status.ID;";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            Item item;
            while (rs.next()) {
                item = new Item();
                item.setTypeId(rs.getInt(7));
                setItemParameter(rs, item);
                items.add(item);
            }
        } catch (SQLException exc) {
            throw new RuntimeException(exc);
        }

        return items;
    }


    private static PreparedStatement selectPreparedStatement(int id) throws SQLException {
        final String SQL = "SELECT " +
                "PRICE,NAME,DESCRIPTION,item_status.ID,STATUS, ITEM.ITEM_TYPE_ID, ITEM_TYPE" +
                " FROM (ITEM INNER JOIN " +
                "item_status " +
                "ON item.ITEM_STATUS_ID=item_status.ID) " +
                "INNER JOIN ITEM_TYPE ON ITEM_TYPE.ID=ITEM.ITEM_TYPE_ID " +
                "WHERE ITEM.ID=?;";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, id);
        return statement;
    }


    public static void readItem(Item tmpItem) {
        try (PreparedStatement statement = selectPreparedStatement(tmpItem.getItemId());
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                tmpItem.setPrice(rs.getFloat(1));
                tmpItem.setName(rs.getString(2));
                tmpItem.setDescription(rs.getString(3));
                tmpItem.setStatusId(rs.getInt(4));
                tmpItem.setItemStatus(rs.getString(5));
                tmpItem.setTypeId(rs.getInt(6));
                tmpItem.setType(rs.getString(7));
                //Преобразуем Blob в строку формата base 64
                            /*Blob ph = rs.getBlob(7);
                            tmpItem.setImage(ph.getBytes(1, (int) ph.length()));*/
            }
        } catch (Exception exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
    }

}