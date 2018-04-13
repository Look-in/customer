package dao.request;

import dao.ChangeInstance;
import entity.Clothes;
import jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ClothesDao extends ItemDao implements ChangeInstance<Clothes> {


    private static ClothesDao instance;

    public static ClothesDao getInstance() {
        if (instance == null) {
            instance = new ClothesDao();
        }
        return instance;
    }

    private PreparedStatement createPreparedStatement(Connection connection, Clothes entity) throws SQLException {
        final String sql = "INSERT INTO CLOTHES "
                + "(ID,SEASON) VALUES "
                + "(?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.getItemId());
        statement.setString(2, entity.getSeason());
        return statement;
    }

    private PreparedStatement updatePreparedStatement(Connection connection, Clothes entity) throws SQLException {
        final String sql = "UPDATE CLOTHES SET "
                + "SEASON = ? "
                + "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.getSeason());
        statement.setInt(2, entity.getItemId());
        return statement;
    }


    @Override
    public void create(Clothes entity) {
        //Заполняет базовую таблицу товара.
        createItem(entity);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = createPreparedStatement(connection, entity)) {
            //Заполняет таблицу свойств Clothes
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Clothes entity) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = updatePreparedStatement(connection, entity)) {
            updateItem(entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            return;
        }
        final String sql = "DELETE FROM ITEM "
                + "WHERE id = ?";
        //Try с ресурсами закрывает коннект после заверш обработки запроса
        //На каждый запрос свой коннект, что замедляет работу
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception exc) {
            throw new RuntimeException(
                    "Error reading DB:" + exc.getMessage());
        }


    }

}
