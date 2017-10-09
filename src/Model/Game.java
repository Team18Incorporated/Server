package Model;

import java.util.ArrayList;
import java.util.List;



public class Game {

    private List<Player> playerList;
    private String gameID; //could make ID's into something other than Strings
    private int status=0;  //We could change these to enums or something but for now 0=in lobby/waiting   1= started

    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public Game(Player player1)
    {
        playerList = new ArrayList<Player>();
        playerList.add(player1);
        status=0;
    }

    //METHODS---------------------------------------------------------------------------------------

    /*addPlayer adds a Player to the game while in the lobby
    *
    *@pre playerList cannot be null, newPlayer cannot be null, playerList.size < 5 , status ==0 (in lobby)
    *@post adds newPlayer to the playerList of the Game being created. Returns boolean to indicate if the Player was successfully added
    * */
    public boolean addPlayer(Player newPlayer)
    {
        if (playerList.size()<5 && status==0 )
        {
            playerList.add(newPlayer);
            return true;
        }
        else
        {
            return false;
        }
    }


    /*startGame sets the Game status to started
    *
    * @pre status==0
    * @post status=1
    * */
    public void startGame()
    {
        status=1;
    }
}
