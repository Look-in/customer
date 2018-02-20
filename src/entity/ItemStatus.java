package entity;

public abstract class ItemStatus{
    private int itemStatusId;
    private String status;

    public ItemStatus(int itemStatusId, String status) {
        this.itemStatusId=itemStatusId;
        this.status=status;
    }

    public ItemStatus() {
    }

    protected void setItemStatusId(int itemStatusId){
        this.itemStatusId=itemStatusId;
    }
    protected void setStatus(String status) {
        this.status=status;
    }

   @Override
   public String toString() {
        return status;
   }
}
