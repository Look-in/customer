package entity.event;


import entity.SetException;
import sun.misc.BASE64Encoder;


public class Item {
    private int itemId;
    private float price;
    private String name;
    private String description;
    private byte[] image;
    private int itemStatusId;
    private int typeId;
    private String type;
    private String itemStatus;

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Item(int itemId, float price, String name, String description, int statusId) {
        this.itemStatusId = statusId;
        this.itemId = itemId;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Item() {

    }

    public int getItemStatusId() {
        return itemStatusId;
    }

    public void setStatusId(int itemStatusId) {
        this.itemStatusId = itemStatusId;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getItemId() {
        return itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    // @Test
    public void setPrice(float price) {
        if (price <= 0) throw new SetException("Error. Price not specified");
        else this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    // encoding the byte image string
    //удалить после отладки, если будет реализован иной метод
    public String getBase64imageFile() {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        StringBuilder imageString = new StringBuilder();
        imageString.append("data:image/png;base64,");
        imageString.append(base64Encoder.encode(image));
        return imageString.toString();
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item [Id =" + itemId + ", price=" +
                String.format("%.2f", price) + ", name=" + name + " description= " +
                description + " statusId=" + itemStatusId + " type=" + type + " typeId=" + typeId;
    }
}







