import java.util.ArrayList;

public class SessionInfos
{
	
	//Class attributes
    private int building_id;
    private ArrayList<Purchases> purchases;
    
    //getter
    public int getBuildingId() { return this.building_id; }
    //setter
    public void setBuildingId(int building_id) { this.building_id = building_id; }

    
  //getter
    public ArrayList<Purchases> getPurchases() { return this.purchases; }
  //setter
    public void setPurchases(ArrayList<Purchases> purchases) { this.purchases = purchases; }
}