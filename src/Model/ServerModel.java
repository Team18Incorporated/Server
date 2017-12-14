package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javafx.util.Pair;
import Commands.InGameCommands.StartTurnCommand;
import Common.ICommand;
import Common.IGameDAO;
import Common.IUserDAO;
import Server.ClientProxy;

public class ServerModel {

	// Variables
	private static ServerModel singleton;
	private HashMap<String, User> users;
	private HashMap<String, GameInfo> gameInfo;
	private HashMap<String, Game> gameList;
	private ArrayList<String> games;
	private ArrayList<String> joinableGames;
	private HashMap<String, User> authTokens;
	private int maxNumCommands;
	
	private IUserDAO userDAO;
	private IGameDAO gameDAO;
	
	

	/*
	 * Users -username -password -joined games
	 */

	// mostrecent
	public ServerModel() {
		// TODO Auto-generated constructor stub
		users = new HashMap<String, User>();
		gameInfo = new HashMap<String, GameInfo>();
		games = new ArrayList<String>();
		joinableGames = new ArrayList<String>();
		authTokens = new HashMap<String, User>();
		gameList = new HashMap<String, Game>();
	}

	/*
	 * getSingleton
	 * 
	 * @post a singleton will be returned
	 */
	public static ServerModel getSingleton() {
		if (singleton == null)
			singleton = new ServerModel();
		return singleton;
	}

	public AuthToken registerUser(User user) {
		if (users.containsKey(user.getUsername()))
			return null;
		users.put(user.getUsername(), user);
		AuthToken temp = new AuthToken();
		authTokens.put(temp.getToken(), user);
		userDAO.register(user, temp);
		return temp;
	}

	public User login(String username, String password) {
		User temp = (User) users.get(username);
		AuthToken tempAuth = null;
		if (temp != null && password.equals(temp.getPassword())) {
			tempAuth = new AuthToken();
			temp.addAuthToken(tempAuth);
			authTokens.put(tempAuth.getToken(), temp);
			userDAO.login(temp, tempAuth);
		}
		return temp;
	}

	public GameInfo newGame(String name, User user) {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(user.getID(), user.getUsername(),
				Player.Color.RED));
		GameInfo temp = new GameInfo(name, players);
		gameInfo.put(temp.getID(), temp);
		user.join(temp.getID());
		users.put(user.getID(), user);
		joinableGames.add(temp.getID());
		return temp;
	}

	public boolean checkAuthToken(AuthToken authToken) {
		return authTokens.containsKey(authToken.getToken());
	}

	public User getUserFromAuthToken(AuthToken authToken) {
		return authTokens.get(authToken.getToken());
	}

	public GameInfo join(String gameID, User userFromAuthToken) {
		users.get(userFromAuthToken.getUsername()).join(gameID);
		GameInfo temp = gameInfo.get(gameID);
		temp.addPlayer(new Player(userFromAuthToken.getID(), userFromAuthToken
				.getUsername(), temp.getNextColor()));
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
		return joinableGames;
	}

	public StartedGameResult startGame(String gameID, String playerID) {
		StartedGameResult result;
		GameInfo gameInfoTemp = gameInfo.get(gameID);
		Game g = null;
		if (!gameInfoTemp.hasStarted()) {
			if (gameInfoTemp.getNumPlayers() < 2) {
				result = new StartedGameResult(false);
				return result;
			}
			// create new game and add to next function
			g = new Game(gameInfoTemp.getPlayers(), gameID, gameInfoTemp.getName());
			gameInfoTemp.start();
			joinableGames.remove(gameID);
			gameList.put(gameID, g);
			// tell other clients game is started
			for (int i = 0; i < gameInfoTemp.getPlayers().size(); i++) {
				Player p = gameInfoTemp.getPlayers().get(i);
				users.get(p.getPlayerName()).startGame(gameID);
				ArrayList<DestinationCard> cards =(ArrayList<DestinationCard>) g.drawDestinationCards();
				//p.addDestinationCards(cards);
				ClientProxy proxy = new ClientProxy(gameID,p.getPlayerID());
				proxy.showDestinationCardChoices(cards);
			}
			for (int i = 0; i < g.getPlayerList().size(); i++) {
				Player p = g.getPlayerList().get(i);
				ClientProxy proxy = new ClientProxy( gameID,p.getPlayerID());
				proxy.updateDestinationDeckSize(g.getNumDestinationDeck());
			}

			
			
			Player p = g.getPlayerList().get(g.getPlayerTurn());
			ClientProxy proxy = new ClientProxy(g.getGameID(),p.getPlayerID());
			proxy.startPlayerTurn();
			gameDAO.storeGame(g);
		}
		else
			g = gameList.get(gameID);
		result = new StartedGameResult(true, g, playerID);
		//System.out.println("Here");

		return result;
	}
	
	public void endGame(String gameID) {
		gameList.remove(gameID);
		gameInfo.remove(gameID);
	}

	public GameInfo getGameInfo(String id) {
		return gameInfo.get(id);
	}

	public Game getGame(String gameID) {
		// TODO Auto-generated method stub
		return gameList.get(gameID);
	}
	
	public void setMaxNumCommands(int num)
	{
		maxNumCommands=num;
	}
	
	private void storeGame(Game game)
	{
		gameDAO.storeGame(game);
	}
	
	public void storeCommand(ICommand command, String gameID)
	{
		Game g = gameList.get(gameID);
		if(maxNumCommands==g.getNumCommandsStored())
		{
			storeGame(g);
			g.resetNumCommandsStored();
			gameDAO.clearCommands(gameID);

		}
		else
		{
			gameDAO.addCommand(command,gameID);
			g.incrementNumCommandsStored();
		}
			
			
	}
	private void deleteGame(String gameID)
	{
		gameDAO.deleteGame(gameID);
	}
	
	
	public void setUpPersistenceProvider(String type)
	{
		//sets up DB based on type
		
		//might not need
	}

	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO=userDAO;
	}
	public void setGameDAO(IGameDAO gameDAO)
	{
		this.gameDAO=gameDAO;
	}
	public void loadGames()
	{
		ArrayList<Game> tempGameList=(ArrayList) gameDAO.loadGames();
		for(Game g: tempGameList)
		{
			gameList.put(g.getGameID(), g);

			ArrayList<ICommand> tempCommandList=(ArrayList) gameDAO.loadCommands(g.getGameID());

			gameDAO.clearCommands(g.getGameID());
			
			for(ICommand command: tempCommandList)
			{
				command.execute();
			}
			gameInfo.put(g.getGameID(), new GameInfo(g));

		}
	}
	
	public void loadUsers()
	{
		ArrayList<User> temp = (ArrayList)userDAO.loadUsers();
		for(User u :temp)
		{
			users.put(u.getUsername(), u);
			u.orphanGameKiller();
		}
		
		ArrayList<Pair<String, String>> tokens = (ArrayList)userDAO.loadAuthTokens();
		for(int i=0; i< tokens.size(); i++)
		{
			authTokens.put(tokens.get(i).getKey(), users.get(tokens.get(i).getValue()));
		}
	}
	
	
	
	public void updateUser(User user)
	{
		userDAO.updateUser(user);
	}
	
	public void clearDB()
	{
		userDAO.clear();
		gameDAO.clear();
	}
}
