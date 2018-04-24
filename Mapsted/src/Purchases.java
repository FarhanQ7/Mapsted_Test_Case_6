public class Purchases
{
	
	
	//Class attributes
    private int item_id;
    private int item_category_id;
    private double cost;
    
    
    
//getter
    public int getItemId() { return this.item_id; }
//setter
    public void setItemId(int item_id) { this.item_id = item_id; }

   
  //getter
    public int getItemCategoryId() { return this.item_category_id; }
  //setter
    public void setItemCategoryId(int item_category_id) { this.item_category_id = item_category_id; }

   
  //getter
    public double getCost() { return this.cost; }
  //setter
    public void setCost(double cost) { this.cost = cost; }
}