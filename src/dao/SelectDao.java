package dao;
/**
 * <code>DAO</code> For reading atributes of items<br>
 *
 * @author Serg Shankunas
 */

import entity.event.Item;

public interface SelectDao{

    /**
     * Считывает поля объекта Item
     *
     * @param id код товара {@link Item}
     */

    Item readItem(int id);

}




