package entity;


import entity.SetPriceException;

public class Item extends ItemStatus{
    private int itemId;
    private float price;
    private String name;
    private String description;

    public Item(int itemId, float price, String name, String description,int itemStatusId, String status) {
        super(itemStatusId,status);
        this.itemId=itemId;
        this.price=price;
        this.name=name;
        this.description=description;
     }
    public Item() {
        super();
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
    // @Test
    public void setPrice(float price){
        if (price<0) throw new SetPriceException("Error. Price<0");
        else this.price=price;
    }
    public void setName(String name) {
        this.name=name;
    }
    public void setDescription(String description) {
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
    @Override
    public void setItemStatusId(int itemStatusId){
        super.setItemStatusId(itemStatusId);
    }
    @Override
    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public String toString() {
        return "Item [itemId =" + itemId + ", price=" +
                String.format("%.2f", price) + ", name=" + name + " description= "+
                description + "] " + super.toString();
    }

}

