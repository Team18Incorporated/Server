package Model;

import android.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class GameMap {

    ArrayList< Route> routeList = new ArrayList<>();
    ArrayList<City> cityList= new ArrayList<>();

    public GameMap()
    {
        createMap();
    }


    public void claimRoute(Route route, Player player)
    {
        route.setOwner(player.getPlayerID());
        player.claimRoute(route);
    }


    private void createMap()
    {
        try {
            createCities();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            createRoutes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void createCities() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("citynames.txt"));
        while(scanner.hasNextLine())
        {
            String city=scanner.nextLine();
            cityList.add(new City(city));
        }
    }

    private void createRoutes() throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("routes.txt"));
        while(scanner.hasNextLine())
        {
            String cityname1 = scanner.next();
            String cityname2 = scanner.next();
            String color = scanner.next();
            String length = scanner.next();

            City city1 = getCity(cityname1);
            City city2 = getCity(cityname2);
            Route newRoute = new Route(city1, city2, color, length);
            routeList.add(newRoute);
            city1.addConnectedRoute(newRoute);
            city2.addConnectedRoute(newRoute);
        }
    }


    private City getCity(String name)
    {
        for(int i=0; i<cityList.size(); i++)
        {
            if(cityList.get(i).getCityName().equals(name))
            {
                return cityList.get(i);
            }
        }
        return null;
    }
}
