package Common;

import java.util.ArrayList;
import java.util.List;



public class GameInfo {

    private String gameID;
    private int numPlayers;
    private ArrayList<Player> playerList;
    private ArrayList<String> playerNames;

    //CONSTRUCTOR-----------------------------------------------------------------------------------

    public GameInfo(String gameID, ArrayList<Player> playerList) {
        this.gameID = gameID;
        this.playerList=(ArrayList<Player>) playerList;
        numPlayers=this.playerList.size();
        for(int i=0; i<numPlayers; i++)
        {
            playerNames.add(this.playerList.get(i).getPlayerName());
        }
    }

    //METHODS---------------------------------------------------------------------------------------
    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }
}
