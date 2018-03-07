package entity;


import entity.event.Item;

import java.util.Map;

public class Bicycle extends Item {
    private String fork;
    private String brakes;
    private String frame;

    public Bicycle(int itemId, float price, String name, String description, int statusId, String itemStatus, String fork, String brakes, String frame) {
        super(itemId, price, name, description, statusId);
        this.fork = fork;
        this.brakes = brakes;
        this.frame = frame;
    }

    public Bicycle() {
    }

    public String getFork() {
        return fork;
    }

    public void setFork(String fork) {
        this.fork = fork;
    }

    public String getBrakes() {
        return brakes;
    }

    public void setBrakes(String brakes) {
        this.brakes = brakes;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "fork='" + fork + '\'' +
                ", brakes='" + brakes + '\'' +
                ", frame='" + frame + '\'' +
                '}'+super.toString();
    }

    @Override
    public void setItemAttributes(Map<String, String[]> attributes) {
        String[] str;
        str = attributes.get("fork");
        if (str.length > 0) fork = str[0];
        str = attributes.get("brakes");
        if (str.length > 0) brakes = str[0];
        str = attributes.get("frame");
        if (str.length > 0) frame = str[0];
        super.setItemAttributes(attributes);
    }
}




