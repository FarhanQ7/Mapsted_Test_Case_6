import java.util.ArrayList;

public class SessionInfos
{
    private int building_id;

    public int getBuildingId() { return this.building_id; }

    public void setBuildingId(int building_id) { this.building_id = building_id; }

    private ArrayList<Purchases> purchases;

    public ArrayList<Purchases> getPurchases() { return this.purchases; }

    public void setPurchases(ArrayList<Purchases> purchases) { this.purchases = purchases; }
}