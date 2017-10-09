package Model;

import java.util.Vector;
import Common.*;

public class User {
	
	private String username;
	private String password;
	private String id;
	private Vector<AuthToken> authtokens;
	private Vector<String> startedGames;
	private Vector<String> gameLobbys;

	public User(String user, String password, String id) {
		// TODO Auto-generated constructor stub
		username = user;
		this.password = password;
		this.id = id;
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
		gameLobbys.add(gameID);
	}

	public void leave(String gameID) {
		// TODO Auto-generated method stub
		gameLobbys.remove(gameID);
	}

	public Object getInProgressGames() {
		// TODO Auto-generated method stub
		return startedGames.toArray();
	}

	public Object getUnstartedGames() {
		// TODO Auto-generated method stub
		return gameLobbys.toArray();
	}

	public void startGame(String gameID) {
		// TODO Auto-generated method stub
		gameLobbys.remove(gameID);
		startedGames.add(gameID);
	}

}
