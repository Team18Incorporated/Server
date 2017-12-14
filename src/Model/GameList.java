package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class GameList implements Serializable{

    private List<GameInfo> gameList;


    //CONSTRUCTOR ----------------------------------------------------------------------------------
    public GameList()
    {
        gameList = new ArrayList<GameInfo>();
    }

    public GameList(ArrayList<GameInfo> gameList)
    {
        this.gameList=gameList;
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

    public GameInfo getGameByID(String gameID) {
        for(int i = 0; i < gameList.size(); i++){
            if(gameList.get(i).getID().equals(gameID)){
                return gameList.get(i);
            }
        }
        return null;
    }

    public int getSize(){ return gameList.size();}
    public GameInfo getGame(int position) {return gameList.get(position);}
}

