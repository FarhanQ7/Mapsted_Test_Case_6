import java.util.ArrayList;

public class Building_ID {


        private int id;

        private ArrayList<Double> Cost = new ArrayList<Double>();
        private double TotalCost;

        //setter method
        public void setId(int id) {
            this.id = id;

        }

        //getter method
        public int getId() {
            return id;
        }

        //setter method
        public void setCost(double cost) {
            Cost.add(cost);
        }
      //getter method
        public ArrayList<Double> getCost() {
            return Cost;
        }
      //setter method
    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }
  //getter method
    public double getTotalCost() {
        return TotalCost;
    }
}
