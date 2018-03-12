package dao.request;


import dao.SelectDao;
import entity.Clothes;
import entity.event.Item;
import entity.event.ItemFactory;
import jdbc.JdbcConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectClothesDao implements SelectDao<Clothes>{


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

@Override
    public void readItem(Clothes item) {
        //Заполнить базовые свойства
        ItemFactory.getDefaultItemDao().readItem(item);
        try (PreparedStatement statement = selectPreparedStatement(item.getItemId());
             ResultSet rs = statement.executeQuery();) {
            while (rs.next()){
                        item.setSeason(rs.getString(1));
                        }
                } catch (Exception exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
    }
}
