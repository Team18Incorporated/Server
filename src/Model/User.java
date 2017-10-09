package Model;

import java.util.Vector;
import Common.*;

public class User {
	
	private String username;
	private String password;
	private Vector<AuthToken> authtokens;
	private Vector<GameInfo> startedGames;
	private Vector<GameInfo> gameLobbys;

	public User(String user, String password) {
		// TODO Auto-generated constructor stub
		username = user;
		this.password = password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}

}
