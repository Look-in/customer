package entity.event;

import dao.ChangeInstance;
import dao.SelectDao;
import dao.request.*;
import entity.Bicycle;
import entity.Clothes;

public class ItemFactory {

    public static Item createItem(int id) {
        switch (id) {
            case 1:
                return new Clothes();
            case 2:
                return new Bicycle();
            default:
                return null;
        }
    }

    public static ChangeInstance getItemInstanceDao(int id) {
        switch (id) {
            case 1:
                return ClothesDao.getInstance();
            case 2:
                return BicycleDao.getInstance();
            default:
                return null;
        }
    }

    public static SelectItemStatusDao getItemStatusDao() {
        return SelectItemStatusDao.getInstance();
    }

    public static SelectTypeDao getItemTypeDao() {
        return SelectTypeDao.getInstance();
    }

    public static SelectDefaultItemDao getDefaultItemDao() {
        return SelectDefaultItemDao.getInstance();
    }

    public static SelectDao getItemDao(int id) {
        switch (id) {
            case 1:
                return SelectClothesDao.getInstance();
            case 2:
                return SelectBicycleDao.getInstance();
            default:
                return null;
        }
    }
}