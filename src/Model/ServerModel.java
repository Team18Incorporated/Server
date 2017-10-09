package Model;

import java.util.HashMap;

public class ServerModel {

	//Variables 
	private static ServerModel singleton;
	private HashMap<String, User> users;
	private HashMap<String, GameInfo> gameInfo;
	private Vector<String> games;
	private Vector<String> joinableGames;
	
	
	/*
	 * ToDo:
	 * Singleton
	 * Joinable Games (list)
	 * Users (hash?)
	 * Games (hash)
	 * GameInfo (all games as a hash)
	 * AuthTokens (and associated users)
	 */
	
	/*
	 * Users
	 * -username
	 * -password
	 * -joined games
	 */
	
	public ServerModel() {
		// TODO Auto-generated constructor stub
		users = new HashMap<String, User>();
	}
	
	
	/*
	 * getSingleton
	 * @post a singleton will be returned
	 */
	public static ServerModel getSingleton(){
		if (singleton == null) singleton = new ServerModel();
		return singleton;
	}
	
	public boolean registerUser(User user){
		if(users.containsKey(user.getUsername()))
			return false;
		users.put(user.getUsername(), user);
		return true;
	}
	
	public boolean findUser(String username, String password){
		User temp = (User) users.get(username);
		if(password == temp.getPassword())
			return true;
		return false;
	}
	
	public GameInfo newGame(){
		
	}
	
}
