package dao;
/**
 * <code>DAO</code> For change instances<br>
 *
 * @author Serg Shankunas
 */

import entity.event.Item;

public interface ChangeInstance<T extends Item> {

    /**
     * Добавляет единицу товара.
     *
     * @param entity сущность {@link Item}
     */

    void create(T entity);

    /**
     * Обновляет позицию товара.
     *
     * @param entity сущность {@link Item}
     */
    void update(T entity);

    /**
     * Удаляет позицию товара.
     *
     * @param id сущности {@link Item}
     */
    void delete(Integer id);
}




