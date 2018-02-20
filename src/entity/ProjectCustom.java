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
        try { // 1 блок
            cn = getConnection(url, prop);
            Statement st = null;
            try { // 2 блок
                st = cn.createStatement();
                ResultSet rs = null;
                try { // 3 блок
                    rs = st.executeQuery("SELECT * FROM ITEMS");
                    ArrayList<Item> lst = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        float price = rs.getFloat(2);
                        String name = rs.getString(3);
                        byte status=rs.getByte(4);
                        lst.add(new Item(id, price, name,status));
                    }
                    if (lst.size() > 0) {
                        for (Item it:lst) System.out.println(it);
                    } else {
                        System.out.println("Not found");
                    }
                    // 3 блок
                        rs = st.executeQuery("SELECT * FROM ITEMS");
                        ArrayList<Item> lst = new ArrayList<>();
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            float price = rs.getFloat(2);
                            String name = rs.getString(3);
                            byte status=rs.getByte(4);
                            lst.add(new Item(id, price, name,status));
                        }
                        if (lst.size() > 0) {
                            for (Item it:lst) System.out.println(it);
                        } else {
                            System.out.println("Not found");
                        }

                    } finally { // для 3-го блока try
                    /*
                     * закрыть ResultSet, если он был открыт
                     * или ошибка произошла во время
                     * чтения из него данных
                     */
                    if (rs != null) { // был ли создан ResultSet
                        rs.close();
                    } else {
                        System.err.println(
                                "ошибка во время чтения из БД");
                    }
                }
            } finally {
                /*
                 * закрыть Statement, если он был открыт или ошибка
                 * произошла во время создания Statement
                 */
                if (st != null) { // для 2-го блока try
                    st.close();
                } else {
                    System.err.println("Statement не создан");
                }
            }
        } catch (SQLException e) { // для 1-го блока try
            System.err.println("DB connection error: " + e);
            /*
             * вывод сообщения о всех SQLException
             */
        } finally {
            /*
             * закрыть Connection, если он был открыт
             */
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }
        }


    }


}
