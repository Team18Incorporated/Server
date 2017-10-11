package Model;

import java.util.ArrayList;
import java.util.Vector;

import Common.*;

public class User {
	
	private String username;
	private String password;
	private String id;
	private ArrayList<AuthToken> authTokens;
	private ArrayList<String> startedGames;
	private ArrayList<String> gameLobbys;

	public User(String user, String password, String id) {
		// TODO Auto-generated constructor stub
		username = user;
		this.password = password;
		this.id = id;
		authTokens = new ArrayList<>();
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
	}

}
