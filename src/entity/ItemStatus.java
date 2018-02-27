package entity;

public class ItemStatus{
    private int itemStatusId;
    private String status;

    public ItemStatus(int itemStatusId, String status) {
        this.itemStatusId=itemStatusId;
        this.status=status;
    }

    public ItemStatus() {
    }

    public void setItemStatusId(int itemStatusId){
        this.itemStatusId=itemStatusId;
    }

    public String getStatus(){
        return status;
    }

    public int getItemStatusId(){
        return itemStatusId;
    }
    public void setStatus(String status) {
        this.status=status;
    }

   @Override
   public String toString() {
        return "[Status id= "+itemStatusId+ " - "+status+"]";
   }
}
