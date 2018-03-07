package entity;

import entity.event.Item;

import java.util.Map;

public class Clothes extends Item {
    private String season;

    public Clothes(int itemId, float price, String name, String description, int statusId, String itemStatus, String season) {
        super(itemId, price, name, description, statusId);
        this.season = season;
    }

    public Clothes() {

    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "season='" + season + '\'' +super.toString()+
                '}';
    }

    @Override
    public void setItemAttributes(Map<String, String[]> attributes) {
        super.setItemAttributes(attributes);
        String[] str;
        str = attributes.get("season");
        if (str.length > 0) season = str[0];
    }
}



