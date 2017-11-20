package Model;

import java.util.ArrayList;
import java.util.List;

import Server.ClientProxy;



public class Game {

    private List<Player> playerList;
    private String gameID; //could make ID's into something other than Strings
    private GameMap map;
    private ArrayList<TrainCard> faceUpCards;
    private TrainDeck trainCardDeck;
    private DestinationDeck destinationDeck;
    private int playerTurn;
    
    private boolean lastRound = false;
    private int lastTurn=-1;
  
    
    private ChatHistory chatHistory;

    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public Game(ArrayList<Player> playerList, String gameID)
    {
    	this.gameID = gameID;
        this.playerList=playerList;
        startGame();
    }

    //METHODS---------------------------------------------------------------------------------------


    public List<Player> getPlayerList() {
        return playerList;
    }

    public String getGameID() {
        return gameID;
    }

    public GameMap getMap() {
        return map;
    }

    public ArrayList<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public int getNumTrainDeck() {
        return trainCardDeck.getSize();
    }

    public int getNumDestinationDeck() {
        return destinationDeck.getSize();
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
    
    public Player getPlayer(String playerID){
    	for(Player p : playerList){
    		if(p.getPlayerID().equals(playerID)) return p;
    	}
    	return null;
    }

    private void startGame()
    {
    	trainCardDeck = new TrainDeck();
    	trainCardDeck.shuffle();
    	destinationDeck = new DestinationDeck();
    	destinationDeck.shuffle();
    	faceUpCards = new ArrayList<TrainCard>();
        faceUpCards.addAll(trainCardDeck.drawCards(5));
        for(int i=0; i<playerList.size(); i++)
        {
        	//add all cards to deck
        	//remove face up cards from deck and update for each client
            playerList.get(i).addCardstoHand(trainCardDeck.drawCards(4));
    		
            //add cards to hand and remove from deck
            ArrayList<DestinationCard> list =destinationDeck.drawCards(3);
            playerList.get(i).setDestinationCardChoices(list);
            playerList.get(i).addDestinationCards(list);
            //add cards to hand and remove from deck
        }
        playerTurn = 0;
        chatHistory = new ChatHistory();
        map = new GameMap();
    }

    public TrainCard drawFaceUpCard(int index)
    {
        TrainCard card = faceUpCards.get(index);
        TrainCard newCard = trainCardDeck.drawCard();
        faceUpCards.add(index, newCard);
        return card;
    }
    
    public List<DestinationCard> drawDestinationCards(){
    	return destinationDeck.drawCards(3);
    }
    
    public TrainCard drawTrainCard(){
    	return trainCardDeck.drawCard();
    }
    
    public void discard(List<DestinationCard> list){
    	destinationDeck.discard(list);
    }
    
    public void sendMsg(ChatMessage msg){
    	chatHistory.add(msg);
    }
    
    public ChatHistory getChatHistory(){
    	return chatHistory;
    }

    public void incrementTurn()
    {
    	if(playerTurn==playerList.size()-1)
    	{
    		playerTurn=0;
    	}
    	else
    	{
    		playerTurn++;
    	}
    	
    }
    
    public ArrayList<TrainCard> discardPlayerCards(String playerID, ArrayList<Integer> discard)
    {
    	Player p= getPlayer(playerID);
    	trainCardDeck.discard(p.discardTrainCards(discard));
    	return p.getHand();
    	
    }

    public void markLastTurn(Player player)
    {
    	for(int i=0; i<playerList.size(); i++)
    	{
    		if(player.getPlayerID().equals(playerList.get(i).getPlayerID()))
    		{
    			lastTurn=i;
    		}
    	}
    }
    
    public boolean checkLastTurn()
    {
    	if(playerTurn==lastTurn)
    	{
    		return true;
    	}
    	return false;
    }
}
