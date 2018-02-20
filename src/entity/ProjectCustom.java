package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import entity.Order;
import entity.Item;

import static java.sql.DriverManager.*;

public class ProjectCustom {
    public static void main(String[ ] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/BARBER";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "sql");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        Connection cn = null;
        registerDriver(new com.mysql.jdbc.Driver());

        ArrayList<Item> items = new ArrayList<>();
        try {
            cn = getConnection(url, prop);
            Statement st = null;
            try {
                st = cn.createStatement();
                ResultSet rs = null;
                try {

                    rs = st.executeQuery("SELECT " +
                            "item.ID,PRICE,NAME,DESCRIPTION,item_status.ID,STATUS" +
                            " FROM ITEM INNER JOIN " +
                            "item_status " +
                            "ON item.ITEM_STATUS_ID=item_status.ID; ");
                    while (rs.next()) {
                        Item tmpItem=new Item();
                        tmpItem.setItemId(rs.getInt(1));
                        tmpItem.setPrice(rs.getFloat(2));
                        tmpItem.setName(rs.getString(3));
                        tmpItem.setDescription(rs.getString(4));
                        tmpItem.setItemStatusId(rs.getInt(5));
                        tmpItem.setStatus(rs.getString(6));
                        items.add(tmpItem);
                    }
         /*               rs = st.executeQuery("SELECT * FROM ITEMS");
                        ArrayList<Item> lst = new ArrayList<>();
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            float price = rs.getFloat(2);
                            String name = rs.getString(3);
                            byte status=rs.getByte(4);
                            lst.add(new Item(id, price, name,status));
                        }
                        if (items.size() > 0) {
                            for (Item it:items) System.out.println(it);
                        } else {
                            System.out.println("Not found");
                        }*/

                    }
                    finally {
                    if (rs != null) {
                        rs.close();
                    }
                    else {
                        System.err.println(
                                "ошибка во время чтения из БД");
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                } else {
                    System.err.println("Statement не создан");
                }
            }
        } catch (SQLException e) {
            System.err.println("DB connection error: " + e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }

            if (items.size() > 0) {
                for (Item it:items) System.out.println(it);
            } else {
                System.out.println("Not found");
            }

        }


    }


}
