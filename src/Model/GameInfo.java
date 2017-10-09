package Model;

import java.util.ArrayList;


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
    
    public String getID(){
    	return gameID;
    }

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		if(numPlayers <=5){
			numPlayers++;
			playerList.add(player);
			playerNames.add(player.getPlayerName());
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

	public Player[] getPlayers() {
		// TODO Auto-generated method stub
		return (Player[]) playerList.toArray();
	}
}
