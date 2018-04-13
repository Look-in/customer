package dao.request;


import dao.SelectDao;
import entity.Clothes;
import entity.event.ItemFactory;
import jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectClothesDao implements SelectDao<Clothes> {


    private static SelectClothesDao instance;

    public static SelectClothesDao getInstance() {
        if (instance == null) {
            instance = new SelectClothesDao();
        }
        return instance;
    }


    private PreparedStatement selectPreparedStatement(Connection connection, int id) throws SQLException {
        final String sql = "SELECT " +
                "SEASON" +
                " FROM CLOTHES " +
                "WHERE ID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public void readItem(Clothes item) {
        //Заполнить базовые свойства
        ItemFactory.getDefaultItemDao().readItem(item);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = selectPreparedStatement(connection, item.getItemId());
             ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                item.setSeason(rs.getString(1));
            }
        } catch (Exception exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
    }
}
