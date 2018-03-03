package dao.request;


import entity.Clothes;
import entity.event.Item;

import java.sql.*;
import java.util.ArrayList;
import static jdbc.JdbcConnect.connect;


public class SelectClothesDao {

    public static ArrayList<Item> readListItem() {
        ArrayList<Item> items = new ArrayList<>();
         //Try с ресурсами закрывает коннект после заверш обработки запроса
        //На каждый запрос свой коннект, что замедляет работу
        try (Connection cn= connect()) {
                try (Statement st = cn.createStatement()) {
                    ResultSet rs = null;
                    try {
                         rs = st.executeQuery("SELECT " +
                                "item.ID,PRICE,NAME,DESCRIPTION,item_status.ID,STATUS" +
                                " FROM ITEM INNER JOIN " +
                                "item_status " +
                                "ON item.ITEM_STATUS_ID=item_status.ID; ");
                        while (rs.next()) {
                            Clothes tmpItem = new Clothes();
                            tmpItem.setItemId(rs.getInt(1));
                            tmpItem.setPrice(rs.getFloat(2));
                            tmpItem.setName(rs.getString(3));
                            tmpItem.setDescription(rs.getString(4));
                            tmpItem.setItemStatusId(rs.getInt(5));
                            tmpItem.setItemStatus(rs.getString(6));
                            //Преобразуем Blob в строку формата base 64
                            /*Blob ph = rs.getBlob(7);
                            tmpItem.setImage(ph.getBytes(1, (int) ph.length()));*/
                            items.add(tmpItem);
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
                } catch (Exception exc) {
                    System.err.println("Сonnection close error: " + exc);
                }

        return items;

    }

    public Item readItem(int id) {
        //Try с ресурсами закрывает коннект после заверш обработки запроса
        //На каждый запрос свой коннект, что замедляет работу
        final String sql="SELECT " +
                "item.ID,PRICE,NAME,DESCRIPTION,item_status.ID,STATUS" +
                " FROM ITEM INNER JOIN " +
                "item_status " +
                "ON item.ITEM_STATUS_ID=item_status.ID " +
                "WHERE item.ID=?;";
        Clothes tmpItem = new Clothes();

        try (Connection connection= connect()) {

            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
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
