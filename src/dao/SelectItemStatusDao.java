package dao;

import entity.ItemStatus;
import java.util.List;

public interface SelectItemStatusDao {

     List<ItemStatus> readItemStatuses();

     String readItemStatus(int itemStatusId);
}
