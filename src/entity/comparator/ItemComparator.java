package entity.comparator;

import entity.event.Item;

import java.util.ArrayList;
import java.util.Collections;

public class ItemComparator {

    public static void compare(ArrayList<Item> item, String sortBy) {
        switch (sortBy) {
            case "Price":
                Collections.sort(item, (o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
                break;
            case "Price DESC":
                Collections.sort(item, (o1, o2) -> (int) (o2.getPrice() - o1.getPrice()));
                break;
            case "Name":
                Collections.sort(item, (o1, o2) -> (o1.getName().compareToIgnoreCase(o2.getName())));
                break;
            case "Status":
                Collections.sort(item, (o1, o2) -> (o1.getItemStatusId() - o2.getItemStatusId()));
                break;
        }
    }
}
