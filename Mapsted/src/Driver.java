import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;


public class Driver {

	//The following code handles requests and are used to get data in the get data functions
    private static OkHttpClient client = new OkHttpClient();
    private static OkHttpClient client2 = new OkHttpClient();

    private static ArrayList < MyPojo[] > DataObjects = new ArrayList < MyPojo[] > ();
    private static ArrayList < Building_Data[] > BuildingDataObjects = new ArrayList < Building_Data[] > ();
    




    //handles GET request
    public static String getJson(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();


        return response.body().string();
    }

//--------------------------------------------------




// gets data from API url
    public void getData() {

        String json = null;

        try {

            json = getJson("http://jobs.mapsted.com/api/Values/GetAnalyticsData");


        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        MyPojo[] pojo = gson.fromJson(json, MyPojo[].class);

        for (int i = 0; i < pojo.length; i++) {

            DataObjects.add(pojo);

        }

    }


    //---------------------------------------------------------
  //handles GET request
    public String getJson2(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client2.newCall(request).execute();


        return response.body().string();
    }



    //---------------------------------------------------------





 // gets data from API url
    public void getData2() {

        String json = null;

        try {

            json = getJson("http://jobs.mapsted.com/api/Values/GetBuildingData");


        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Building_Data[] build_data = gson.fromJson(json, Building_Data[].class);

        for (int i = 0; i < build_data.length; i++) {

            BuildingDataObjects.add(build_data);
        }

    }


    //---------------------------------------------------------

// Finds Total purchase costs for Samsung manufacture devices 
    public  int getSamsung() {
        int device_num = 0;
        String samsung = "Samsung";

        for (int i = 0; i < DataObjects.size(); i++) {

            if (DataObjects.get(i)[i].getManufacturer().equals(samsung)) {
                device_num++;
            }
        }
        return device_num;
    }

//---------------------------------------------------------

// rounds double to specified decimal place
    public  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

//---------------------------------------------------------


//finds  Total number of times item (item_id = 47) was purchased 
    public int getitem47() {
        int device_num = 0;
        int item_47 = 47;

        for (int i = 0; i < DataObjects.size(); i++) { // loops through each object
            for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) { // loops through each list with in each object
                for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) { // loops through each item in each list
                	// adds to device if item 47 is found
                    if (DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getItemId() == 47) {
                        device_num++;
                    }
                }
            }
        }
        return device_num;
    }


//---------------------------------------------------------

    //Finds Total purchase costs for item’s in the category (item_category_id = 7) 
    public double get_cost_item7() {
        ArrayList < Double > Costs = new ArrayList < Double > ();

        for (int i = 0; i < DataObjects.size(); i++) {// loops through each object
            for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) { // loops through each list with in each object
                for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) { // loops through each item in each list
                	//if category id is 7, cost is added to cost list for calculation
                    if (DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getItemCategoryId() == 7) {
                        Costs.add(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                    }
                }
            }
        }
        double costs = 0.0;
        //The following code adds the total purchase price by adding the costs in the list
        for (int h = 0; h < Costs.size(); h++) {
            costs = costs + Costs.get(h);
        }
        return round(costs, 2);

    }


//---------------------------------------------------------



//Finds Total purchase costs in Ontario 
    public double Ontario_cost() {

        ArrayList < String > id = new ArrayList < String > (); // stores building id
        ArrayList < Double > Cost = new ArrayList < Double > (); // costs associated with id 

        // in the for loop all of the building ids associated with Ontario are added to the building id list
        for (int i = 0; i < BuildingDataObjects.size(); i++) {
            if (BuildingDataObjects.get(i)[i].getState().equals("Ontario")) {
                id.add(BuildingDataObjects.get(i)[i].getBuilding_id());

            }


        }

        for (int n = 0; n < id.size(); n++) { // loops through the number of ids in buliding id list
            for (int i = 0; i < DataObjects.size(); i++) {// loops through each object
                for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {// loops through each list with in each object
                    for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {// loops through each item in each list
                    	//if the building id in building id list is the same as the building id in DataObjects add associated to cost to Cost list
                        if (Integer.parseInt(id.get(n)) == DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getBuildingId()) {
                            Cost.add(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                        }
                    }
                }
            }
        }


        double cost = 0.0;
        // finds the total cost
        for (int m = 0; m < Cost.size(); m++) {

            cost = cost + Cost.get(m);
        }

        return round(cost, 2);


    }

//---------------------------------------------------------



    //Finds  Total purchase costs in the United States 
    public double Us_cost() {

        ArrayList < String > id = new ArrayList < String > (); //stores building id
        ArrayList < Double > Cost = new ArrayList < Double > (); //costs associated with id 

        // For loop gets building id associated with United States
        for (int i = 0; i < BuildingDataObjects.size(); i++) { // loops through each object
            if (BuildingDataObjects.get(i)[i].getCountry().equals("United States")) {
                id.add(BuildingDataObjects.get(i)[i].getBuilding_id());
                

            }


        }





        for (int n = 0; n < id.size(); n++) { // loops through number of bulding ids
            for (int i = 0; i < DataObjects.size(); i++) { // loops through each object
                for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {// loops through each list with in each object
                    for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {// loops through each item with in each list
                    	// adds prices associated with bulding ids associated with United States
                        if (Integer.parseInt(id.get(n)) == DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getBuildingId()) {
                            
                            Cost.add(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                        }
                    }
                }
            }
        }

        double cost = 0.0;
        // adds the total purchase price
        for (int m = 0; m < Cost.size(); m++) {

            cost = cost + Cost.get(m);
        }

        return round(cost, 2);

    }

    //---------------------------------------------------------

    // adder is used to add prices of objects in most_item functions
    public double adder(ArrayList<Double> list){


        double cost = 0;


        for(int i =0 ; i < list.size(); i++){

            cost = cost+list.get(i);


        }

        return cost;



    }
//---------------------------------------------------------

    //Function that performs insertion sort
    void sort( ArrayList<Building_ID> arr )
    {
        Building_ID val;
        int holePos;

        // At the beginning of each iteration arr[0..k-1] has been sorted, and the loop then
        // inserts element k into its proper place
        for( int k=1; k<arr.size(); k++ )
        {
            // make a copy of the value to insert
            val = arr.get(k);

            // The kth position is where the "hole" starts
            holePos = k;

            // Move the "hole" left until we either get the the end of the array
            // or the element left of the hole is <= to the value we're inserting
            while( holePos > 0 && arr.get(holePos-1).getTotalCost() > val.getTotalCost() )
            {
                // move the hole to the left
                arr.set( holePos, arr.get(holePos-1) );
                holePos--;
            }

            // copy the value into the hole
            arr.set( holePos, val );
        }
    }

//---------------------------------------------------------

    public double most_item() {


        Building_ID item = new Building_ID(); // object used to store building id and associated costs
        ArrayList<Building_ID> items = new ArrayList<Building_ID>(); // used to store building id objects
        ArrayList<Double> Cost = new ArrayList<Double>(); // used to store costs associated with building id objects

        for (int i = 0; i < 50; i++) { // creates building id objects and adds them to building id list 

            item.setId(i);
            items.add(item);
        }
        for (int n =0; n<items.size(); n++){ //loops through building id lists 
            for (int i = 0; i < DataObjects.size(); i++) {// loops through each Data Object
                for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {// loops through each list in each Data Object
                    // adds associated price of each object to objects in building id list
                	if (items.get(n).getId() == DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getBuildingId()){

                        for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {//loops through each item in each list in each Data Object
                        	 
                            items.get(n).setCost(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                        }


                    }
                }
            }
    }


    //adds total cost of each buildingid
    for (int l = 0; l <items.size(); l++){

        items.get(l).setTotalCost(adder(items.get(l).getCost()));
    }
    //sorts list in ascending order 
    sort(items);
    //returns largest value
    return round(items.get(items.size()-1).getId(),2);


    }

}