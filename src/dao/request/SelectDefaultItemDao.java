package dao.request;

import entity.event.Item;
import java.util.ArrayList;

public interface SelectDefaultItemDao {

    ArrayList<Item> readListItem(int typeItemId);

    ArrayList<Item> readListItem();

    void readItem(Item item);

}