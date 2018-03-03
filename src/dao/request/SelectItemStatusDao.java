package dao.request;

import dao.ChangeInstance;
import entity.Clothes;
import entity.ItemStatus;
import entity.event.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static jdbc.JdbcConnect.connect;

public class SelectItemStatusDao {

    public static ArrayList<ItemStatus> readItemStatus() {
        ArrayList<ItemStatus> status = new ArrayList<>();
        //Try с ресурсами закрывает коннект после заверш обработки запроса
        //На каждый запрос свой коннект, что замедляет работу
        try (Connection cn= connect()) {
            try (Statement st = cn.createStatement()) {
                ResultSet rs = null;
                try {
                    rs = st.executeQuery("SELECT " +
                            "ID,STATUS" +
                            " FROM ITEM_STATUS");
                    while (rs.next()) {
                        ItemStatus tmpStatus = new ItemStatus();
                        tmpStatus.setItemStatusId(rs.getInt(1));
                        tmpStatus.setItemStatus(rs.getString(2));
                        status.add(tmpStatus);
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

        return status;

    }

}
