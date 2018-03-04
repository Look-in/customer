package dao;

import entity.event.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static jdbc.JdbcConnect.connect;

public abstract class SelectDefaultItemDao {

    public static Item readItem(Item tmpItem) {
        final String sql="SELECT " +
                "item.ID,PRICE,NAME,DESCRIPTION,item_status.ID,STATUS" +
                " FROM ITEM INNER JOIN " +
                "item_status " +
                "ON item.ITEM_STATUS_ID=item_status.ID " +
                "WHERE item.ID=?;";
        try (Connection connection= connect()) {

            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, tmpItem.getItemId());
                rs = statement.executeQuery();
                while (rs.next()){
                    tmpItem.setItemId(rs.getInt(1));
                    tmpItem.setPrice(rs.getFloat(2));
                    tmpItem.setName(rs.getString(3));
                    tmpItem.setDescription(rs.getString(4));
                    tmpItem.setItemStatusId(rs.getInt(5));
                    tmpItem.setItemStatus(rs.getString(6));
                    //Преобразуем Blob в строку формата base 64
                            /*Blob ph = rs.getBlob(7);
                            tmpItem.setImage(ph.getBytes(1, (int) ph.length()));*/
                }
            } catch (Exception exc) {
                throw new RuntimeException(
                        "Error reading DB:" + exc.getMessage());
            }
            finally {if (rs != null) rs.close();
            }

        } catch (Exception exc) {
            throw new RuntimeException("Satement can't closed: "+exc);
        }
        return tmpItem;

    }


}

