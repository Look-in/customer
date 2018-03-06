package dao.request;

import entity.event.ItemType;
import jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectTypeDao {

    public static ArrayList<ItemType> readType() {
        ArrayList<ItemType> type = new ArrayList<>();
        final String sql ="SELECT ID,ITEM_TYPE "+
                "FROM ITEM_TYPE;";
        Connection connection= JdbcConnect.getInstance().connect();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
             while (rs.next()) {
                 ItemType tmpType = new ItemType(rs.getInt(1),rs.getString(2));
                 type.add(tmpType);
                 }
                } catch (Exception exc) {
                    throw new RuntimeException(
                            "Error reading DB:" + exc.getMessage());
                }
        return type;
    }

}