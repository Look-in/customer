package entity;


import entity.event.Item;

public class Clothes extends Item {
    private String season;

    public Clothes(int itemId, float price, String name, String description, int statusId, String itemStatus, String season) {
        super(itemId, price, name, description, statusId, itemStatus);
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
}



