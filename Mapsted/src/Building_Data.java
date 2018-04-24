public class Building_Data
{
	//Class attributes
    private String building_name;

    private String building_id;

    private String state;

    private String country;

    private String city;
    //getter
    public String getBuilding_name ()
    {
        return building_name;
    }
    //setter
    public void setBuilding_name (String building_name)
    {
        this.building_name = building_name;
    }
    //getter
    public String getBuilding_id ()
    {
        return building_id;
    }
  //setter
    public void setBuilding_id (String building_id)
    {
        this.building_id = building_id;
    }
  //getter
    public String getState ()
    {
        return state;
    }
  //setter
    public void setState (String state)
    {
        this.state = state;
    }
  //getter
    public String getCountry ()
    {
        return country;
    }
  //setter
    public void setCountry (String country)
    {
        this.country = country;
    }
  //getter
    public String getCity ()
    {
        return city;
    }
  //setter
    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [building_name = "+building_name+", building_id = "+building_id+", state = "+state+", country = "+country+", city = "+city+"]";
    }
}