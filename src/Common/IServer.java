package Common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.AuthToken;
import Model.ChatMessage;
import Model.GameInfo;
import Model.Route;
import Model.StartedGameResult;
import Commands.CommandList;
import Commands.ShowDestinationChoicesCommand;
import Commands.UpdateFaceUpCommand;
import Model.DestinationCard;
import Model.TrainCard;

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
	public GameInfo join(AuthToken authToken, String gameID);
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
	/*public UpdateFaceUpCommand drawFromFaceUp(AuthToken authToken, String gameID, TrainCard card);

	public UpdateChatHistoryCommand sendChat(AuthToken authToken, ChatMessage chatMessage);
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
	public StartedGameResult startGame(String gameID, AuthToken token);

	/*
	* @pre route is not null
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a list of commands to be executed on the client.
	 */
	public void claimRoute(AuthToken authToken, String gameID, Route route, ArrayList<Integer> discard);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a list of commands (UpdateTrainHand and UpdateTrainDeckSize)
	 */
	public void drawTrainCard(AuthToken authToken, String gameID);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a command that displays the player's choosable destination cards.
 	*/
	public void drawDestinationCard(AuthToken authToken, String gameID);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a list of commands
	 */
	public void sendBackDestinations(AuthToken authToken, String gameID, List<DestinationCard> list);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @pre card is not null
	* @post returns a list containing an update hand command and update
	 */
	public void drawFromFaceUp(AuthToken authToken, String gameID, int card);

	public void sendChat(AuthToken authToken, ChatMessage chatMessage, String gameID);
	
	public CommandList getHistory(AuthToken authToken, String gameID, int index);
	
	public void forfeit(AuthToken token, String gameID);

}
