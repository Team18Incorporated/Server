package Model;

import java.util.ArrayList;
import java.util.List;



public class Game {

    private List<Player> playerList;
    private String gameID; //could make ID's into something other than Strings
    private GameMap map;
    private ArrayList<TrainCard> faceUpCards;
    private TrainDeck trainCardDeck;
    private DestinationDeck destinationDeck;
    private int playerTurn;

    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public Game(ArrayList<Player> playerList)
    {
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

    private void startGame()
    {
    	trainCardDeck = new TrainDeck();
    	trainCardDeck.shuffle();
    	destinationDeck = new DestinationDeck();
    	destinationDeck.shuffle();
        for(int i=0; i<playerList.size(); i++)
        {
            playerList.get(i).addCardstoHand(trainCardDeck.drawCards(4));
            playerList.get(i).addDestinationCards(destinationDeck.drawCards(3));
        }
        faceUpCards=trainCardDeck.drawCards(4);
        playerTurn = 0;
    }

    public TrainCard drawFaceUpCard(int index)
    {
        TrainCard card = faceUpCards.get(index);
        TrainCard newCard = trainCardDeck.drawCards(1).get(0);
        faceUpCards.add(index, newCard);
        return card;
    }
    
    public List<DestinationCard> drawDestinationCards(){
    	return destinationDeck.drawCards(3);
    }
    
    public TrainCard drawTrainCard(){
    	return trainCardDeck.drawCard();
    }



}
