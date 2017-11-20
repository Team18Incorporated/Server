package Model;


import java.util.ArrayList;

public class DestinationCard extends Card {

    City startCity;
    City endCity;
    int points;

    public DestinationCard(String startCityName, String endCityName, int points) {
        startCity= new City(startCityName);
        endCity= new City(endCityName);
        this.points=points;
    }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public int getPoints() {return points;}
    
    public boolean checkComplete(Player player)
    {
        ArrayList<Route> routeList = player.getClaimedRoutes();
        for(int i=0; i<routeList.size(); i++)
        {
            if(routeList.get(i).getCity1()==startCity)
            {
                return checkRoute(startCity, routeList.get(i), routeList, i);
            }
            else if(routeList.get(i).getCity2()==startCity)
            {
                return checkRoute(startCity, routeList.get(i), routeList, i);
            }
        }
        return false;
    }

    private boolean checkRoute(City currentCity, Route route, ArrayList<Route> routeList, int index)
    {
        ArrayList<Route> listcopy= routeList;
        listcopy.remove(index);
        City checkCity=null;
        if(route.getCity1()==currentCity)
        {
            checkCity=route.getCity2();
        }
        else if(route.getCity2()==currentCity)
        {
            checkCity=route.getCity1();
        }

        if(checkCity==endCity)
        {
            return true;
        }
        for(int i=0; i<listcopy.size(); i++)
        {
            if(listcopy.get(i).getCity1()==checkCity)
            {
                return checkRoute(checkCity, listcopy.get(i), listcopy, i);
            }
            else if(listcopy.get(i).getCity2()==checkCity)
            {
                return checkRoute(checkCity, listcopy.get(i), listcopy, i);
            }
        }


        return false;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DestinationCard)) return false;

        DestinationCard that = (DestinationCard) o;

        if (points != that.points) return false;
        if (!startCity.equals(that.startCity)) return false;
        return endCity.equals(that.endCity);
    }
}
