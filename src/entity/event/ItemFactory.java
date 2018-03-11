package entity.event;

import dao.ChangeInstance;
import dao.SelectDao;
import dao.request.*;
import entity.Bicycle;
import entity.Clothes;

public class ItemFactory {

    public static Item createItem(int id) {
        switch (ItemFactory.getItemTypeDao().readItemType(id)) {
            case "CLOTHES":
                return new Clothes();
            case "BICYCLE":
                return new Bicycle();
            default:
                return null;
        }
    }

    public static ChangeInstance getItemInstanceDao(int id) {
        switch (ItemFactory.getItemTypeDao().readItemType(id)) {
            case "CLOTHES":
                return ClothesDao.getInstance();
            case "BICYCLE":
                return BicycleDao.getInstance();
            default:
                return null;
        }
    }

    public static SelectItemStatusDao getItemStatusDao() {
        return SelectItemStatusDao.getInstance();
    }

    public static SelectItemTypeDao getItemTypeDao() {
        return SelectItemTypeDao.getInstance();
    }

    public static SelectDefaultItemDao getDefaultItemDao() {
        return SelectDefaultItemDao.getInstance();
    }

    public static SelectDao getItemDao(int id) {
        switch (ItemFactory.getItemTypeDao().readItemType(id)) {
            case "CLOTHES":
                return SelectClothesDao.getInstance();
            case "BICYCLE":
                return SelectBicycleDao.getInstance();
            default:
                return null;
        }
    }
}