package Common;


public interface IServer {
	/*
	 * logs user in and returns authToken. Throws login exceptions
	 * @Pre: user&&password !=null && != ""
	 * @Post: 0 < int authToken < 10000 || exception
	 */
	public AuthToken userLogin(String user, String password);
	/*
	 * Registers a new user and logs them in. Returns authToken
	 * Throws existingUser exception
	 * @Pre: user&&password != null && != ""
	 * @Post: 0 < int authToken < 10000 || existingUser exception 
	 */
	public AuthToken registerUser(String user, String password);
	/*
	 * creates a new game, using authToken to determine the creator. Returns gameID
	 * @Pre: 0 < authToken < 10000
	 * @Post: 0 < int gameID < 10000
	 */
	public GameInfo newGame(AuthToken authToken, String name);
	/*
	 * adds authToken user to gameID game
	 * @Pre: 0 < authToken&&gameID < 10000
	 * @Post: None
	 */
	public void join(AuthToken authToken, String gameID);
	/*
	 * removes authToken user from gameID game
	 * @Pre: 0 < authToken&&gameID < 10000
	 * @Post: None
	 */
	public void leave(AuthToken authToken, String gameID);
	/*
	 * returns a list of the join-able games on the server
	 * @Pre: None
	 * @Post: Object gamesList !=null && isType List<String> of gameIDs
	 */
	public Object openGames();
	/*
	 * returns a list of in-progress games authToken user is currently in
	 * @Pre: 0 < authToken < 10000
	 * @Post: Object gamesList !=null && isType List<String> of gameIDs
	 */
	public Object inProgressGames(AuthToken authToken);
	/*
	 * returns a list of unstarted games authToken user is currently in
	 * @Pre: 0 < authToken < 10000
	 * @Post: Object gamesList !=null && isType List<String> of gameIDs
	 */
	public Object unstartedGames(AuthToken authToken);
	/*
	 * flags gameID game as started. Initializes game objects for gameID
	 * @Pre: 0 < gameID < 10000
	 * @Post: None
	 */
	public void startGame(String gameID);

}
