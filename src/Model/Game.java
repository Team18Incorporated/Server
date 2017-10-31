package Model;

import java.util.ArrayList;
import java.util.List;



public class Game {

    private List<Player> playerList;
    private String gameID; //could make ID's into something other than Strings
    private GameMap map;
    private ArrayList<TrainCard> faceUpCards;
    private Deck trainCardDeck;
    private Deck destinationDeck;
    private int playerTurn;

    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public Game(List<Player> playerList)
    {
        this.playerList=playerList;
        trainCardDeck = new Deck(0);
        destinationDeck = new Deck(1);
        trainCardDeck.shuffle();
        destinationDeck.shuffle();
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

    public void setNumTrainDeck(int numTrainDeck) {
        this.numTrainDeck = numTrainDeck;
    }
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    private void startGame()
    {
        for(int i=0; i<playerList.size(); i++)
        {
            playerList.get(i).addCardstoHand(trainCardDeck.drawCards(4));
            playerList.get(i).addDestinationCards(destinationDeck.drawCards(3));
        }
        faceUpCards=trainCardDeck.drawCards(4);
    }

    public Card drawFaceUpCard(int index)
    {
        Card card = visibleCards.get(index);
        Card newCard = trainCardDeck.drawCards(1).get(0);
        visibleCards.add(index, newCard);
        return card;
    }



}
