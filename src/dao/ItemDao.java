package dao;

import entity.Item;
import jdbc.JdbcConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDao extends RuntimeException{

    public ArrayList<Item> readItem() throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        JdbcConnect connection=new JdbcConnect();
        try (         Connection cn=connection.connect()) {
                //
                try (Statement st = cn.createStatement();) {
                    //Statement st = null;


             //       st =
                    ResultSet rs = null;
                    try {
                         rs = st.executeQuery("SELECT " +
                                "item.ID,PRICE,NAME,DESCRIPTION,item_status.ID,STATUS" +
                                " FROM ITEM INNER JOIN " +
                                "item_status " +
                                "ON item.ITEM_STATUS_ID=item_status.ID; ");
                        while (rs.next()) {
                            Item tmpItem = new Item();
                            tmpItem.setItemId(rs.getInt(1));
                            tmpItem.setPrice(rs.getFloat(2));
                            tmpItem.setName(rs.getString(3));
                            tmpItem.setDescription(rs.getString(4));
                            tmpItem.setItemStatusId(rs.getInt(5));
                            tmpItem.setStatus(rs.getString(6));
                            items.add(tmpItem);
                        }
                     } catch (Exception exc) {
                        System.err.println(
                                "ошибка во время чтения из БД " + exc.getMessage());
                    }
                    finally {if (rs != null) rs.close();
                    }

                    } catch (Exception exc) {
                        System.err.println("Statement не создан " + exc.getMessage());
                    }
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }

        return items;

    }
}
