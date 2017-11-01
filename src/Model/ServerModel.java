package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class ServerModel {

	//Variables 
	private static ServerModel singleton;
	private HashMap<String, User> users;
	private HashMap<String, GameInfo> gameInfo;
	private HashMap<String, Game> gameList;
	private ArrayList<String> games;
	private ArrayList<String> joinableGames;
	private HashMap<String, User> authTokens;
	
	
	/*
	 * Users
	 * -username
	 * -password
	 * -joined games
	 */
	
	
	//mostrecent
	public ServerModel() {
		// TODO Auto-generated constructor stub
		users = new HashMap<String, User>();
		gameInfo = new HashMap<String, GameInfo>();
		games = new ArrayList<String>();
		joinableGames = new ArrayList<String>();
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
		AuthToken tempAuth = null;
		if(temp!=null && password.equals(temp.getPassword())){
			tempAuth = new AuthToken();
			authTokens.put(tempAuth.getToken(), temp);
		}
		return tempAuth;
	}
	
	public GameInfo newGame(String name, User user){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(user.getID(), user.getUsername(), Player.Color.RED));
		GameInfo temp = new GameInfo(name, players);
		gameInfo.put(temp.getID(), temp);
		user.join(temp.getID());
		users.put(user.getID(), user);
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


	public GameInfo join(String gameID, User userFromAuthToken) {
		// TODO Auto-generated method stub
		users.get(userFromAuthToken.getUsername()).join(gameID);
		GameInfo temp = gameInfo.get(gameID);
		temp.addPlayer(new Player(userFromAuthToken.getID(), userFromAuthToken.getUsername(), temp.getNextColor()));
		userFromAuthToken.join(gameID);
		gameInfo.put(gameID, temp);
		
		return temp;	
		
	}
	
	public void leave(String gameID, User userFromAuthToken) {
		users.get(userFromAuthToken.getUsername()).leave(gameID);
		GameInfo temp = gameInfo.get(gameID);
		temp.removePlayer(userFromAuthToken.getID());
		gameInfo.put(gameID, temp);
	}


	public Object getOpenGames() {
		// TODO Auto-generated method stub
		return joinableGames;
	}


	public StartedGameResult startGame(String gameID) {
		// TODO Auto-generated method stub
		StartedGameResult result;
		GameInfo gameInfoTemp = gameInfo.get(gameID);
		if(gameInfoTemp.getNumPlayers()<2)
		{
			result= new StartedGameResult(false);
			return result;
		}
		gameInfoTemp.start();
		joinableGames.remove(gameID);
		for(int i = 0; i < gameInfoTemp.getPlayers().size(); i++){
			Player p = gameInfoTemp.getPlayers().get(i);
			users.get(p.getPlayerID()).startGame(gameID);
		}
		gameList.put(gameID, new Game(gameInfoTemp.getPlayers()));
		result= new StartedGameResult(true);
		System.out.println("Here");

		return result;
	}
	
	public GameInfo getGameInfo(String id) {
		return gameInfo.get(id);
	}


	public Game getGame(String gameID) {
		// TODO Auto-generated method stub
		return gameList.get(gameID);
	}
}
