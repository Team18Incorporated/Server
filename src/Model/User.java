package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import Common.*;
import Server.ServerFacade;

public class User implements Serializable{
	
	private String username;
	private String password;
	private String id;
	private AuthToken authToken;
	private ArrayList<String> startedGames;
	private ArrayList<String> gameLobbys;

	public User(String user, String password, String id) {
		// TODO Auto-generated constructor stub
		username = user;
		this.password = password;
		this.id = id;
		startedGames = new ArrayList<>();
		gameLobbys = new ArrayList<>();
		}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}

	public String getID() {
		// TODO Auto-generated method stub
		return id;
	}


	public void join(String gameID) {
		// TODO Auto-generated method stub
		if(!gameLobbys.contains(gameID))
			gameLobbys.add(gameID);
		ServerModel.getSingleton().updateUser(this);

	}

	public void leave(String gameID) {
		// TODO Auto-generated method stub
		gameLobbys.remove(gameID);
	}

	public Object getInProgressGames() {
		// TODO Auto-generated method stub
		return  startedGames;
	}

	public Object getUnstartedGames() {
		// TODO Auto-generated method stub
		return gameLobbys;
	}

	public void startGame(String gameID) {
		// TODO Auto-generated method stub
		if(gameLobbys.contains(gameID)) gameLobbys.remove(gameID);
		startedGames.add(gameID);
		ServerModel.getSingleton().updateUser(this);
	}
	
	public void addAuthToken(AuthToken authToken)
	{
		this.authToken=authToken;
	}

	/*
	 * checks that a game has a record, removes it if not
	 */
	public void orphanGameKiller(){
		for (int i=0; i<gameLobbys.size(); i++){
			GameInfo temp = ServerModel.getSingleton().getGameInfo(gameLobbys.get(i));
			if (temp==null){
				gameLobbys.remove(i);
				i--;
			}
		}
	}
}
