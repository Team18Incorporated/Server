package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class ServerModel {

	//Variables 
	private static ServerModel singleton;
	private HashMap<String, User> users;
	private HashMap<String, GameInfo> gameInfo;
	private Vector<String> games;
	private Vector<String> joinableGames;
	private HashMap<String, User> authTokens;
	
	
	/*
	 * Users
	 * -username
	 * -password
	 * -joined games
	 */
	
	public ServerModel() {
		// TODO Auto-generated constructor stub
		users = new HashMap<String, User>();
		gameInfo = new HashMap<String, GameInfo>();
		games = new Vector<String>();
		joinableGames = new Vector<String>();
		authTokens = new HashMap<String, User>();
	}
	
	
	/*
	 * getSingleton
	 * @post a singleton will be returned
	 */
	public static ServerModel getSingleton(){
		if (singleton == null) singleton = new ServerModel();
		return singleton;
	}
	
	public AuthToken registerUser(User user){
		if(users.containsKey(user.getUsername()))
			return null;
		users.put(user.getUsername(), user);
		AuthToken temp = new AuthToken();
		authTokens.put(temp.getToken(), user);
		return temp;
	}
	
	public AuthToken login(String username, String password){
		User temp = (User) users.get(username);
		if(password == temp.getPassword()){
			AuthToken tempAuth = new AuthToken();
			authTokens.put(tempAuth.getToken(), temp);
		}
		return null;
	}
	
	public GameInfo newGame(String name, User user){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(user.getID(), user.getUsername(), Player.Color.RED));
		GameInfo temp = new GameInfo(name, players);
		gameInfo.put(temp.getID(), temp);
		users.get(user.getUsername()).join(temp.getID());
		joinableGames.add(temp.getID());
		return temp;
	}


	public boolean checkAuthToken(AuthToken authToken) {
		// TODO Auto-generated method stub
		return authTokens.containsKey(authToken.getToken());
	}


	public User getUserFromAuthToken(AuthToken authToken) {
		// TODO Auto-generated method stub
		return authTokens.get(authToken.getToken());
	}


	public void join(String gameID, User userFromAuthToken) {
		// TODO Auto-generated method stub
		users.get(userFromAuthToken.getUsername()).join(gameID);
		GameInfo temp = gameInfo.get(gameID);
		temp.addPlayer(new Player(userFromAuthToken.getID(), userFromAuthToken.getUsername(), temp.getNextColor()));
		userFromAuthToken.join(gameID);
		gameInfo.put(gameID, temp);
	}
	
	public void leave(String gameID, User userFromAuthToken) {
		users.get(userFromAuthToken.getUsername()).leave(gameID);
		GameInfo temp = gameInfo.get(gameID);
		temp.removePlayer(userFromAuthToken.getID());
		gameInfo.put(gameID, temp);
	}


	public Object getOpenGames() {
		// TODO Auto-generated method stub
		return joinableGames.toArray();
	}


	public void startGame(String gameID) {
		// TODO Auto-generated method stub
		GameInfo gameInfoTemp = gameInfo.get(gameID);
		joinableGames.removeElement(gameID);
		for(int i = 0; i < gameInfoTemp.getNumPlayers(); i++){
			Player p = gameInfoTemp.getPlayers()[i];
			users.get(p.getPlayerID()).startGame(gameID);
		}
	}
	
	public GameInfo getGameInfo(String id) {
		return gameInfo.get(id);
	}
}
