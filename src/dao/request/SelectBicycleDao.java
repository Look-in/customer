package dao.request;


import dao.SelectDao;
import entity.Bicycle;
import entity.event.Item;
import entity.event.ItemFactory;
import jdbc.JdbcConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectBicycleDao implements SelectDao<Bicycle>{


    private static SelectBicycleDao instance;

    public static SelectBicycleDao getInstance(){
        if(instance == null){
            instance = new SelectBicycleDao();
        }
        return instance;
    }


    private PreparedStatement selectPreparedStatement(int id) throws SQLException {
        final String sql="SELECT " +
                "FORK, BRAKES, FRAME" +
                " FROM BICYCLE " +
                "WHERE ID=?;";
        Connection connection= JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement;
    }

@Override
    public void readItem(Bicycle item) {
        //Заполнить базовые свойства
        ItemFactory.getDefaultItemDao().readItem(item);
        Connection connection= JdbcConnect.getInstance().connect();
        try (PreparedStatement statement = selectPreparedStatement(item.getItemId());
            ResultSet rs = statement.executeQuery();) {
            while (rs.next()){
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
