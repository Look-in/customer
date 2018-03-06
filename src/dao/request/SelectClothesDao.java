package dao.request;


import dao.SelectDefaultItemDao;
import entity.Clothes;
import entity.event.Item;
import jdbc.JdbcConnect;

import java.sql.*;
import java.util.ArrayList;

public class SelectClothesDao {


    private static SelectClothesDao instance;

    public static SelectClothesDao getInstance(){
        if(instance == null){
            instance = new SelectClothesDao();
        }
        return instance;
    }


    private PreparedStatement selectPreparedStatement(int id) throws SQLException {
        final String sql="SELECT " +
                "SEASON" +
                " FROM CLOTHES " +
                "WHERE ID=?;";
        Connection connection= JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement;
    }


    public Item readItem(int id) {
        Clothes tmpItem = new Clothes();
        tmpItem.setItemId(id);
        //Заполнить базовые свойства
        SelectDefaultItemDao.readItem(tmpItem);
        Connection connection= JdbcConnect.getInstance().connect();
        try (PreparedStatement statement = selectPreparedStatement(id);
            ResultSet rs = statement.executeQuery();) {
            while (rs.next()){
                        tmpItem.setSeason(rs.getString(1));
                        //Преобразуем Blob в строку формата base 64
                            /*Blob ph = rs.getBlob(7);
                            tmpItem.setImage(ph.getBytes(1, (int) ph.length()));*/
                        }
                } catch (Exception exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
        return tmpItem;

    }
}
