package dao.request;

import dao.ChangeInstance;
import entity.Bicycle;
import jdbc.JdbcConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BicycleDao extends ItemDao implements ChangeInstance<Bicycle>{

    private static BicycleDao instance;

    public static BicycleDao getInstance(){
        if(instance == null){
            instance = new BicycleDao();
        }
        return instance;
    }

    private PreparedStatement createPreparedStatement(Bicycle entity) throws SQLException{
        final String sql ="INSERT INTO BICYCLE "
                + "(ID, FORK, BRAKES, FRAME) VALUES "
                + "(?, ?, ?, ?)";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.getItemId());
        statement.setString(2, entity.getFork());
        statement.setString(3, entity.getBrakes());
        statement.setString(4, entity.getFrame());
        return statement;
    }

    private PreparedStatement updatePreparedStatement(Bicycle entity) throws SQLException{
        final String sql ="UPDATE BICYCLE SET "
                + "FORK = ?, BRAKES=?, FRAME=? "
                + "WHERE id = ?";
        Connection connection = JdbcConnect.getInstance().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.getFork());
        statement.setString(2, entity.getBrakes());
        statement.setString(3, entity.getFrame());
        statement.setInt(4, entity.getItemId());
        return statement;
    }


    @Override
    public void create(Bicycle entity) {
        /*Try с ресурсами закрывает коннект после заверш обработки запроса
        На каждый запрос свой коннект, что замедляет работу, но на тестовом проекте это некритично*/
        Connection connection = JdbcConnect.getInstance().connect();
             //Заполняет базовую таблицу товара.
            createItem(entity);
            try (PreparedStatement statement = createPreparedStatement(entity)) {
                //Заполняет таблицу свойств Clothes
                statement.executeUpdate();
        }
          catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Bicycle entity) {
        Connection connection= JdbcConnect.getInstance().connect();
        try (PreparedStatement statement=updatePreparedStatement(entity)) {
                updateItem(entity);
                statement.executeUpdate();
                }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            return;
        }
        final String sql ="DELETE FROM ITEM "
                        + "WHERE id = ?";
        //Try с ресурсами закрывает коннект после заверш обработки запроса
        //На каждый запрос свой коннект, что замедляет работу
            Connection connection= JdbcConnect.getInstance().connect();
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            catch (Exception exc) {
                throw new RuntimeException(
                        "Error reading DB:" + exc.getMessage());
            }
    }

}
