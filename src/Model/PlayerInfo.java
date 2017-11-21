package Model;

import java.util.ArrayList;

public class PlayerInfo {
    
    private String playerID;
    private String playerName;
    private Player.Color color;
    private int numTrainCards;
    private int numDestinationCards;
    private int points;
    private int penalties = -1;
    private int destinationsCompleted = -1;
    private boolean hasLongestRoute = false;
    private int numTrainPieces;

    public PlayerInfo(Player player)
    {
        playerID=player.getPlayerID();
        playerName=player.getPlayerName();
        color= player.getColor();
        points=player.getPoints();
        numTrainCards=player.getHand().size();
        numDestinationCards=player.getDestinationCards().size();
        numTrainPieces = player.getNumTrainPieces();
        penalties=player.getPenalties();
        destinationsCompleted=player.getDestinationsCompleted();
        hasLongestRoute=player.hasLongestRoute();
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player.Color getColor() {
        return color;
    }

    public int getNumTrainCards() {
        return numTrainCards;
    }

    public int getNumDestinationCards() {
        return numDestinationCards;
    }

    public int getPoints() {
        return points;
    }

    public void setNumTrainCards(int numTrainCards) {
        this.numTrainCards = numTrainCards;
    }

    public void setNumDestinationCards(int numDestinationCards) {
        this.numDestinationCards = numDestinationCards;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
