package Model;

import java.util.ArrayList;



public class Route {

    private City city1;
    private City city2;
    private int length;
    private CardColor color;
    private String ownerID;
    private ArrayList<Integer> segments;


    public Route (String cityName1, String cityName2, int length, CardColor color1)
    {
        this.length=length;
        city1=new City(cityName1);
        city2=new City(cityName2);
        this.color=color1;
        ownerID=null;
    }

    public Route (City city1, City city2, String color, String length)
    {
        this.city1=city1;
        this.city2=city2;
        this.length=Integer.parseInt(length);
        this.color= new CardColor(color);
        ownerID=null;

    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getLength() {
        return length;
    }

    public CardColor getColor() {
        return color;
    }

    public void setOwner(String playerID){ownerID=playerID;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (length != route.length) return false;
        if (!city1.equals(route.city1)) return false;
        if (!city2.equals(route.city2)) return false;
        if (!color.equals(route.color)) return false;
        return ownerID != null ? ownerID.equals(route.ownerID) : route.ownerID == null;

    }
}
