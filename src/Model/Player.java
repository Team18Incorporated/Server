package Model;


import java.util.ArrayList;
import java.util.Date;

import Commands.CommandList;
import Common.ICommand;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;



public class Player {

    private String playerID;
    private String playerName;
    public enum Color{RED, BLUE, GREEN, YELLOW, BLACK}
    private Color color;
    private ArrayList<TrainCard> hand;
    private ArrayList<DestinationCard> destinationCards;
    private int penalties = 0;
    private int destinationsCompleted = 0;
    private int destinationPoints=0;
    private int penaltyPoints=0;
    private int longestRouteLength=0;
    private boolean hasLongestRoute = false;
    private int points=0;
    private ArrayList<Route> claimedRoutes = new ArrayList<>();
    private int numTrainPieces=6;
    private ArrayList<DestinationCard> destinationCardChoices = new ArrayList<>();
    
    private PlayerCommandList commands = new PlayerCommandList();

    //CONSTRUCTOR-----------------------------------------------------------------------------------


    public Player(String playerID, String playerName, Color color) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.color = color;
        hand = new ArrayList<TrainCard>();
        destinationCards = new ArrayList<DestinationCard>();
    }

    /*changePlayerColor changes the color of the player
    *
    * @pre cannot be the same color as another player in the same Game
    * @post changes Player color
    *
    * */
    public void changePlayerColor(Color color) //need a way to find which colors have already been taken
    {
        this.color=color;
    }

    public String getPlayerName(){
	return playerName;
    }

    public String getPlayerID(){
	return playerID;
    }

    public Color getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<TrainCard> getHand() {
        return hand;
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return destinationCards;
    }
    public void discard(ArrayList<DestinationCard> cards){
    	destinationCards.removeAll(cards);
    }

    public ArrayList<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public int getNumTrainPieces() {
        return numTrainPieces;
    }

    public void setNumTrainPieces(int numTrainPieces) {
        this.numTrainPieces = numTrainPieces;
    }

    public void claimRoute(Route route)
    {
        claimedRoutes.add(route);
    }

    
    public void addCardstoHand(ArrayList<TrainCard> cards)
    {
        hand.addAll(cards);
    }

    public void addDestinationCards(ArrayList<DestinationCard> cards)
    {
        destinationCards.addAll(cards);
    }
    public void setDestinationCardChoices(ArrayList<DestinationCard> cards)
    {
    	destinationCardChoices=cards;
    }
    
    public void addCommand(ICommand command){
    	commands.addCommand(command);
    }
    
    public CommandList getCommands(int index){
    	return commands.getCommands(index);
    }
    
    public ArrayList<TrainCard> discardTrainCards(ArrayList<Integer> discard)
    {
    	ArrayList<TrainCard> discardList= new ArrayList<>();
    	for(int i=0; i<discard.get(0); i++)
    	{
    		discardList.add(getCard("black"));
    	}
    	for(int i=0; i<discard.get(1); i++)
    	{
    		discardList.add(getCard("red"));
    	}
    	for(int i=0; i<discard.get(2); i++)
    	{
    		discardList.add(getCard("blue"));
    	}
    	for(int i=0; i<discard.get(3); i++)
    	{
    		discardList.add(getCard("green"));
    	}
    	for(int i=0; i<discard.get(4); i++)
    	{
    		discardList.add(getCard("yellow"));
    	}
    	for(int i=0; i<discard.get(5); i++)
    	{
    		discardList.add(getCard("purple"));
    	}
    	for(int i=0; i<discard.get(6); i++)
    	{
    		discardList.add(getCard("white"));
    	}
    	for(int i=0; i<discard.get(7); i++)
    	{
    		discardList.add(getCard("orange"));
    	}
    	for(int i=0; i<discard.get(8); i++)
    	{
    		discardList.add(getCard("wild"));
    	}
    	
    	return discardList;
    }
    
    public TrainCard getCard(String color)
    {
    	TrainCard card=null;
    	for(TrainCard tc : hand)
    	{
    		if(tc.getColorString().equals(color))
    		{
    			card=tc;
    		}
    	}
    	hand.remove(card);
    	return card;
    }
    
    
    public void addPoints(int length)
    {
    	if(length==1)
    	{
    		points=points+1;
    	}
    	else if(length==2)
    	{
    		points=points+2;
    	}
    	else if(length==3)
    	{
    		points=points+4;
    	}
    	else if(length==4)
    	{
    		points=points+7;
    	}
    	else if(length==5)
    	{
    		points=points+10;
    	}
    	else if(length==6)
    	{
    		points=points+15;
    	}
    }

    public int getPenalties() {return penaltyPoints;}
    public int getDestinationsCompleted() {return destinationPoints;}
    public boolean hasLongestRoute() {
    	return hasLongestRoute;
    }
  
    public void findLongestRoute()
    {
    	ArrayList<City> checkedCities = new ArrayList<>();
    	ArrayList<Route> checkedRoutes = new ArrayList<>();
    	
    	int currentBest=0;
    	for(Route r : claimedRoutes)
    	{
    		checkedRoutes.add(r);
    		int bestFromRoute =0;
    		checkedCities.add(r.getCity1());
    		int firstCityCheck = findLongestContinuousPath(r, checkedCities);
    		checkedCities.clear();
    		checkedCities.add(r.getCity2());
    		int secondCityCheck = findLongestContinuousPath(r, checkedCities);

    		
    		if(firstCityCheck>currentBest)
    		{
    			currentBest=firstCityCheck;
    		}
    		if(secondCityCheck>currentBest)
    		{
    			currentBest=secondCityCheck;
    		}
    		
    		/*Route secondRoute=findInitialConnection(r, checkedCities);
    		if(secondRoute!=null)
    		{
        		bestFromRoute=r.getLength()+findLongestContinuousPath(secondRoute, checkedCities);
        		if(bestFromRoute>currentBest)
        		{
        			currentBest=bestFromRoute;
        		}

    		}*/
    		
    	}
    	longestRouteLength= currentBest;
    }
    
    private int findLongestContinuousPath(Route route, ArrayList<City> checkedCities)
    {
    	City cityToCheck=null;
		if(!checkedCities.contains(route.getCity1()))
		{
			cityToCheck=route.getCity1();
		}
		else if(!checkedCities.contains(route.getCity2()))
		{
			cityToCheck=route.getCity2();
		}
		
    	
		int best=0;
    	for(Route r : claimedRoutes)
    	{
    		int current =0;
    		if(!r.equals(route))
    		{ 				
    			if(r.getCity1().equals(cityToCheck))
    			{
    				checkedCities.add(cityToCheck);
    				current = r.getLength()+findLongestContinuousPath(r, checkedCities);
    			}
    			else if(r.getCity2().equals(cityToCheck))
    			{
    				checkedCities.add(cityToCheck);
    				current = r.getLength()+findLongestContinuousPath(r, checkedCities);
    			}
    		}
    		if(current>best)
    		{
    			best=current;
    		}
    	}
    	
    	
    	return best+route.getLength();
    }
    
    /*private Route findInitialConnection(Route route, ArrayList<City> checkedCities)
    {
    	Route returnRoute=null;
    	for(Route r : claimedRoutes)
    	{
    		if(!r.equals(route))
    		{
    			if(r.getCity1().equals(route.getCity1()))
    			{
    				checkedCities.add(r.getCity1());
    				return r;
    			}
    			else if(r.getCity1().equals(route.getCity2()))
    			{
    				checkedCities.add(r.getCity1());
    				return r;
    			}
    			else if(r.getCity2().equals(route.getCity2()))
    			{
    				checkedCities.add(r.getCity2());
    				return r;
    			}
    			else if(r.getCity2().equals(route.getCity1()))
    			{
    				checkedCities.add(r.getCity1());
    				return r;
    			}
    		}
    	}    	
    	return returnRoute;
    }*/
    
    public int getLongestRouteLength()
    {
    	return longestRouteLength;
    }
    
    
    
    public void setLongestRoute() {
    	//TODO implement logic or else set
    	hasLongestRoute=true;
    }
    
    public void checkDestinationCards() {
    	for (int i=0; i<destinationCardChoices.size(); i++) {
    		DestinationCard temp = destinationCardChoices.get(i);
    		if (temp.checkComplete(this)) {
    			//points = points + temp.getPoints();
    			destinationPoints+=temp.getPoints();
    			destinationsCompleted++;
    		}
    		else {
    			//points = points - temp.getPoints();
    			penaltyPoints-=temp.getPoints();
    			if (points<0) {points = 0;}
    			penalties++;
    		}
    	}
    }
}
