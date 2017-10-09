package Common;


public class Player {

    private int playerID; //we may not need this if we just use the userID
    private String playerName;
    private enum Color{RED, BLUE, GREEN, YELLOW, BLACK}
    private Color color;

    //CONSTRUCTOR-----------------------------------------------------------------------------------


    public Player(int playerID, String playerName, Color color) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.color = color;
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

    public int getPlayerID(){
	return playerID;
    }
}
