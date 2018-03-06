package dao.request;

import entity.ItemStatus;
import jdbc.JdbcConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectItemStatusDao {
    private static SelectItemStatusDao instance;

    public static SelectItemStatusDao getInstance(){
        if(instance == null){
            instance = new SelectItemStatusDao();
        }
        return instance;
    }


    public static ArrayList<ItemStatus> readItemStatus() {
        ArrayList<ItemStatus> status = new ArrayList<>();
        Connection connection= JdbcConnect.getInstance().connect();
             try (Statement st = connection.createStatement()) {
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

        return status;

    }

}
