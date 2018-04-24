import java.util.ArrayList;

public class Building_ID {







        private int id;

        private ArrayList<Double> Cost = new ArrayList<Double>();
        private double TotalCost;


        public void setId(int id) {
            this.id = id;

        }


        public int getId() {
            return id;
        }


        public void setCost(double cost) {
            Cost.add(cost);
        }

        public ArrayList<Double> getCost() {
            return Cost;
        }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }

    public double getTotalCost() {
        return TotalCost;
    }
}
