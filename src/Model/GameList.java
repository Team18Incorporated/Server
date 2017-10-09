package Model;

import java.util.ArrayList;
import java.util.List;



public class GameList {

    private List<GameInfo> gameList;


    //CONSTRUCTOR ----------------------------------------------------------------------------------
    public GameList()
    {
        gameList = new ArrayList<GameInfo>();
    }


    //METHODS---------------------------------------------------------------------------------------

    /*addGame adds a Game to the GameList
    * @pre newGameInfo cannot be null
    * @post adds newGameInfo to gameList
    * */
    public void addGame(GameInfo newGameInfo)
    {
        gameList.add(newGameInfo);
    }
}
