package Model;

import java.io.Serializable;
import java.util.ArrayList;



public class City implements Serializable{

    private String cityName;
    private ArrayList<Route> connectRoutes= new ArrayList<>();

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
       /* if(cityName.equals("ELPaso"))
        {
            return "El Paso";
        }
        else if(cityName.equals("KansasCity"))
        {
            return "Kansas City";
        }
        else if(cityName.equals("LasVegas"))
        {
            return "Las Vegas";
        }
        else if(cityName.equals("LittleRock"))
        {
            return "Little Rock";
        }
        else if(cityName.equals("LosAngeles"))
        {
            return "Los Angeles";
        }
        else if(cityName.equals("NewOrleans"))
        {
            return "New Orleans";
        }
        else if(cityName.equals("OklahomaCity"))
        {
            return "Oklahoma City";
        }
        else if(cityName.equals("SaintLouis"))
        {
            return "Saint Louis";
        }
        else if(cityName.equals("SaltLakeCity"))
        {
            return "Salt Lake City";
        }
        else if(cityName.equals("SanFrancisco"))
        {
            return "San Francisco";
        }else if(cityName.equals("SantaFe"))
        {
            return "Santa Fe";
        }
        else if(cityName.equals("SaultStMarie"))
        {
            return "Sault St Marie";
        }*/

        return cityName;
    }

    public void addConnectedRoute(Route route)
    {
        connectRoutes.add(route);
    }

    public ArrayList<Route> getConnectRoutes() {
        return connectRoutes;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        return cityName.equals(city.cityName);

    }
}
