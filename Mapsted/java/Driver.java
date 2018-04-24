import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;


public class Driver {


    private static OkHttpClient client = new OkHttpClient();
    private static OkHttpClient client2 = new OkHttpClient();

    private static ArrayList < MyPojo[] > DataObjects = new ArrayList < MyPojo[] > ();
    private static ArrayList < Building_Data[] > BuildingDataObjects = new ArrayList < Building_Data[] > ();
    //private static ArrayList<Double> Ontario_cost = new ArrayList<Double>();





    public static String getJson(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();


        return response.body().string();
    }

//--------------------------------------------------





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

    public String getJson2(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client2.newCall(request).execute();


        return response.body().string();
    }



    //---------------------------------------------------------






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


    public  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

//---------------------------------------------------------



    public int getitem47() {
        int device_num = 0;
        int item_47 = 47;

        for (int i = 0; i < DataObjects.size(); i++) {
            for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {
                for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {

                    if (DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getItemId() == 47) {
                        device_num++;
                    }
                }
            }
        }
        return device_num;
    }


//---------------------------------------------------------


    public double get_cost_item7() {
        ArrayList < Double > Costs = new ArrayList < Double > ();
        int item_47 = 47;

        for (int i = 0; i < DataObjects.size(); i++) {
            for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {
                for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {

                    if (DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getItemCategoryId() == 7) {
                        Costs.add(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                    }
                }
            }
        }
        double costs = 0.0;

        for (int h = 0; h < Costs.size(); h++) {
            costs = costs + Costs.get(h);
        }
        return round(costs, 2);

    }


//---------------------------------------------------------




    public double Ontario_cost() {

        ArrayList < String > id = new ArrayList < String > ();
        ArrayList < Double > Cost = new ArrayList < Double > ();

        for (int i = 0; i < BuildingDataObjects.size(); i++) {
            if (BuildingDataObjects.get(i)[i].getState().equals("Ontario")) {
                // System.out.print("This object contains this State/Prov: " + BuildingDataObjects.get(i)[i].getState() + "\n");
                //System.out.print("\n");
                //System.out.print("This object contains this Building_id: " + BuildingDataObjects.get(i)[i].getBuilding_id() + "\n");
                id.add(BuildingDataObjects.get(i)[i].getBuilding_id());
                //System.out.print("\n");

            }


        }

        for (int n = 0; n < id.size(); n++) {
            for (int i = 0; i < DataObjects.size(); i++) {
                for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {
                    for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {

                        if (Integer.parseInt(id.get(n)) == DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getBuildingId()) {
                            Cost.add(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                        }
                    }
                }
            }
        }


        double cost = 0.0;

        for (int m = 0; m < Cost.size(); m++) {

            cost = cost + Cost.get(m);
        }

        return round(cost, 2);


    }

//---------------------------------------------------------




    public double Us_cost() {

        ArrayList < String > id = new ArrayList < String > ();
        ArrayList < Double > Cost = new ArrayList < Double > ();

        for (int i = 0; i < BuildingDataObjects.size(); i++) {
            if (BuildingDataObjects.get(i)[i].getCountry().equals("United States")) {
                //System.out.print("This object contains this State/Prov: " + BuildingDataObjects.get(i)[i].getCountry() + "\n");
                //System.out.print("\n");
                //System.out.print("This object contains this Building_id: " + BuildingDataObjects.get(i)[i].getBuilding_id() + "\n");
                id.add(BuildingDataObjects.get(i)[i].getBuilding_id());
                //System.out.print("\n");

            }


        }





        for (int n = 0; n < id.size(); n++) {
            for (int i = 0; i < DataObjects.size(); i++) {
                for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {
                    for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {

                        if (Integer.parseInt(id.get(n)) == DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getBuildingId()) {
                            //System.out.print("Reached here! \n");
                            Cost.add(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                        }
                    }
                }
            }
        }

        double cost = 0.0;

        for (int m = 0; m < Cost.size(); m++) {

            cost = cost + Cost.get(m);
        }

        return round(cost, 2);

    }

    //---------------------------------------------------------


    public double adder(ArrayList<Double> list){


        double cost = 0;


        for(int i =0 ; i < list.size(); i++){

            cost = cost+list.get(i);


        }

        return cost;



    }
//---------------------------------------------------------

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


        Building_ID item = new Building_ID();
        ArrayList<Building_ID> items = new ArrayList<Building_ID>();
        ArrayList<Double> Cost = new ArrayList<Double>();

        for (int i = 0; i < 50; i++) {

            item.setId(i);
            items.add(item);
        }
        for (int n =0; n<items.size(); n++){
            for (int i = 0; i < DataObjects.size(); i++) {
                for (int j = 0; j < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().size(); j++) {
                    if (items.get(n).getId() == DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getBuildingId()){

                        for (int k = 0; k < DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().size(); k++) {

                            items.get(n).setCost(DataObjects.get(i)[i].getUsageStatistics().getSessionInfos().get(j).getPurchases().get(k).getCost());
                        }


                    }
                }
            }
    }



    for (int l = 0; l <items.size(); l++){

        items.get(l).setTotalCost(adder(items.get(l).getCost()));
    }

    sort(items);

    return round(items.get(items.size()-1).getId(),2);


    }

}