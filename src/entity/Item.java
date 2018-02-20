package entity;

import entity.SetPriceException;

public class Item {
    private int itemId;
    private float price;
    private String name;
    private byte status;

    public Item(int itemId, float price, String name,byte status) {
        this.itemId=itemId;
        this.price=price;
        this.name=name;
        this.status=status;
    }

    public int getItemId() {
        return itemId;
    }
    public float getPrice() {
        return price;
    }
    public String getName() {
        return name;
    };
    public void setItemId(int itemId) {
        this.itemId=itemId;
    }

    public void setStatus(byte status) {
        this.status=status;
    }
    // @Test
    public void setPrice(float price){
        if (price<0) throw new SetPriceException("Error. Price<0");
        else this.price=price;
    }
    public void setName() {
        this.name=name;
    }

    @Override
    public String toString() {
        return "Item [itemId =" + itemId + ", price=" + String.format("%.2f", price) + ", name=" + name + "]";
    }

}

