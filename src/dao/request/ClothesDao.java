package dao.request;

import dao.ChangeInstance;
import entity.Clothes;
import entity.event.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

import static jdbc.JdbcConnect.connect;


public class ClothesDao implements ChangeInstance<Clothes>{

    @Override
    public synchronized void create(Clothes entity) {
        final String sqlItem ="INSERT INTO ITEM "
                + "(ID,PRICE,NAME,ITEM_STATUS_ID) VALUES "
                + "(?, ?, ?, ?)";
        final String sqlClothes ="INSERT INTO CLOTHES "
                + "(ID,SEASON) VALUES "
                + "(?, ?)";
        entity.setItemId(7);
        //Try с ресурсами закрывает коннект после заверш обработки запроса
        //На каждый запрос свой коннект, что замедляет работу
        try (Connection connection= connect()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = null;
            Savepoint savepointOne = connection.setSavepoint("SavepointOne");
                try {
                    //Заполним базовую таблицу товара. Используем транзакцию
                    statement = connection.prepareStatement(sqlItem);
                    statement.setInt(1, entity.getItemId());
                    statement.setFloat(2, entity.getPrice());
                    statement.setString(3, entity.getName());
                    statement.setInt(4, entity.getItemStatusId());
                    statement.executeUpdate();
                        //Заполним таблицу Clothes
                    statement = connection.prepareStatement(sqlClothes);
                    statement.setInt(1, entity.getItemId());
                    statement.setString(2, entity.getSeason());
                    statement.executeUpdate();
                    connection.commit();
                }
                    catch (Exception exc) {
                        connection.rollback(savepointOne);
                        throw new RuntimeException(
                            "Error reading DB:" + exc.getMessage());

                }
                finally {if (statement != null) statement.close();
                }
            } catch (Exception exc) {
            System.err.println("Сonnection error: " + exc);
        }
    }

    @Override
    public void update(Clothes entity) {
        final String sql ="UPDATE ITEM SET "
                + "PRICE = ? , NAME = ? , ITEM_STATUS_ID = ? "
                + "WHERE id = ?";
        //Try с ресурсами закрывает коннект после заверш обработки запроса
        //На каждый запрос свой коннект, что замедляет работу
        try (Connection connection= connect()) {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setFloat(1, entity.getPrice());
                statement.setString(2, entity.getName());
                statement.setInt(3, entity.getItemStatusId());
                statement.setInt(4, entity.getItemId());
                statement.executeUpdate();

            }
            catch (Exception exc) {
                throw new RuntimeException(
                        "Error reading DB:" + exc.getMessage());
            }
            finally {if (statement != null) statement.close();
            }
        } catch (Exception exc) {
            System.err.println("Сonnection error: " + exc);
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
        try (Connection connection= connect()) {
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
            finally {if (statement != null) statement.close();
            }
        } catch (Exception exc) {
            System.err.println("Сonnection error: " + exc);
        }


    }
}
