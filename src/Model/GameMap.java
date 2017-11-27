package Model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;



public class GameMap {

    ArrayList< Route> routeList = new ArrayList<>();
    ArrayList<City> cityList= new ArrayList<>();

    public GameMap()
    {
        createMap();
    }


    public Route claimRoute(Route routeIn, Player player)
    {
        Route route= getRoute(routeIn);
        route.setOwner(player.getPlayerID());
        player.claimRoute(route);
        player.setNumTrainPieces(player.getNumTrainPieces()-route.getLength());
        player.addPoints(route.getLength());
        return route;
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
        URL url = this.getClass().getResource("/" + "citynames.txt");
        InputStream stream = null;
		try {
			stream = url.openStream();
	        Scanner scanner = new Scanner(stream);
	        while(scanner.hasNextLine())
	        {
	            String city=scanner.nextLine();
	            cityList.add(new City(city));
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void createRoutes() throws FileNotFoundException
    {
    	URL url = this.getClass().getResource("/" + "routes.txt");
        InputStream stream = null;

		try {
			stream = url.openStream();
			Scanner scanner = new Scanner(stream);
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
	            //city1.addConnectedRoute(newRoute);
	            //city2.addConnectedRoute(newRoute);
	        }
	        System.out.println("finished routes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    
    private Route getRoute(Route routeIn)
    {
    	Route returnRoute=null;
    	
    	for(Route r : routeList)
    	{
    		if(r.equals(routeIn))
    		{
    			returnRoute=r;
    		}
    	}
    	
    	return returnRoute;
    }
}
