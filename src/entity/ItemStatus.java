package entity;

public class ItemStatus{
    private int itemStatusId;
    private String itemStatus;

    public ItemStatus(int itemStatusId, String itemStatus) {
        this.itemStatusId=itemStatusId;
        this.itemStatus=itemStatus;
    }

    public ItemStatus() {
    }

    public String getItemStatus(){
        return itemStatus;
    }

    public int getItemStatusId(){
        return itemStatusId;
    }

    public void setItemStatusId(int itemStatusId){
        this.itemStatusId=itemStatusId;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus=itemStatus;
    }

   @Override
   public String toString() {
        return "[Status id= "+itemStatusId+ " - "+itemStatus+"]";
   }
}
