public class Building_Data
{
    private String building_name;

    private String building_id;

    private String state;

    private String country;

    private String city;

    public String getBuilding_name ()
    {
        return building_name;
    }

    public void setBuilding_name (String building_name)
    {
        this.building_name = building_name;
    }

    public String getBuilding_id ()
    {
        return building_id;
    }

    public void setBuilding_id (String building_id)
    {
        this.building_id = building_id;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

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