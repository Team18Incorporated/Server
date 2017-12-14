package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


public class GameInfo implements Serializable{

    private String gameID;
    private String gameName;
    private int numPlayers;
    private ArrayList<Player> playerList;
    private ArrayList<String> playerNames;
    private boolean hasStarted = false;
    private boolean maxPlayers=false;

    //CONSTRUCTOR-----------------------------------------------------------------------------------

    public GameInfo(String gameName, ArrayList<Player> playerList) {
        this.gameName = gameName;
        this.gameID = UUID.randomUUID().toString();
        this.playerList= playerList;
        numPlayers=this.playerList.size();
        playerNames = new ArrayList<String>();
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
    
    public String getID(){
    	return gameID;
    }

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		if(numPlayers<5){
			if(checkPlayer(player))
			{
				numPlayers++;
				playerList.add(player);
				playerNames.add(player.getPlayerName());
				
			}
				
			
		}
		else
		{
			maxPlayers=true;
		}
		
	}
	
	public Player.Color getNextColor(){
		switch(numPlayers){
		case 1: return Player.Color.BLUE;
		case 2: return Player.Color.GREEN;
		case 3: return Player.Color.YELLOW; 
		case 4: return Player.Color.BLACK;
		}
		return null;
	}

	public void removePlayer(String id) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < numPlayers; i++){
			if(playerList.get(i).getPlayerID().equals(id)){
				playerList.remove(i);
				playerNames.remove(i);
			}
		}
		numPlayers--;
	}

	public ArrayList<Player> getPlayers() {
		// TODO Auto-generated method stub
		return playerList;
	}
	
	public void start()
	{
		hasStarted=true;
	}
	
	public boolean hasStarted(){
		return hasStarted;
	}
	
	private boolean checkPlayer(Player newPlayer)
	{
		for(int i= 0; i<playerList.size(); i++)
		{
			if(playerList.get(i).getPlayerID()==newPlayer.getPlayerID())
			{
				return false;
			}
		}
		return true;
	}
}
