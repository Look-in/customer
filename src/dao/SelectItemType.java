package dao;

import entity.ItemType;
import java.util.List;

public interface SelectItemType {

     List<ItemType> readItemTypes();

     String readItemType(int itemStatusId);
}