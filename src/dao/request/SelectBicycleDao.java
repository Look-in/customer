package dao.request;


import dao.SelectDao;
import entity.Bicycle;
import entity.event.ItemFactory;
import jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectBicycleDao implements SelectDao<Bicycle> {


    private static SelectBicycleDao instance;

    public static SelectBicycleDao getInstance() {
        if (instance == null) {
            instance = new SelectBicycleDao();
        }
        return instance;
    }


    private PreparedStatement selectPreparedStatement(Connection connection, int id) throws SQLException {
        final String sql = "SELECT " +
                "FORK, BRAKES, FRAME" +
                " FROM BICYCLE " +
                "WHERE ID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public void readItem(Bicycle item) {
        //Заполнить базовые свойства
        ItemFactory.getDefaultItemDao().readItem(item);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = selectPreparedStatement(connection, item.getItemId());
             ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                item.setFork(rs.getString(1));
                item.setBrakes(rs.getString(2));
                item.setFrame(rs.getString(3));
            }
        } catch (Exception exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }
    }
}
