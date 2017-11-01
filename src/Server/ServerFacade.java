package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Model.DestinationCard;
import Common.IServer;
import Model.ChatMessage;
import Model.Game;
import Model.Player;
import Model.Route;
import Model.AuthToken;
import Model.GameInfo;
import Model.GameList;
import Model.ServerModel;
import Model.StartedGameResult;
import Model.TrainCard;
import Model.User;
import Commands.CommandList;
import Commands.ShowDestinationChoicesCommand;
import Commands.UpdateFaceUpCommand;

public class ServerFacade implements IServer {

	private static ServerFacade singleton;

	public static ServerFacade getSingleton() {
		if (singleton == null)
			singleton = new ServerFacade();
		return singleton;
	}

	public ServerFacade() {

	}

	@Override
	public AuthToken userLogin(String user, String password) {
		// TODO Auto-generated method stub
		return ServerModel.getSingleton().login(user, password);
	}

	@Override
	public AuthToken registerUser(String user, String password) {
		// TODO Auto-generated method stub
		return ServerModel.getSingleton().registerUser(
				new User(user, password, UUID.randomUUID().toString()));
	}

	@Override
	public GameInfo newGame(AuthToken authToken, String name) {
		// TODO Auto-generated method stub
		if (ServerModel.getSingleton().checkAuthToken(authToken)) {
			return ServerModel.getSingleton().newGame(name,
					ServerModel.getSingleton().getUserFromAuthToken(authToken));
		} else
			return null;
	}

	@Override
	public GameInfo join(AuthToken authToken, String gameID) {
		// TODO Auto-generated method stub
		if (ServerModel.getSingleton().checkAuthToken(authToken))
			return ServerModel.getSingleton().join(gameID,
					ServerModel.getSingleton().getUserFromAuthToken(authToken));
		else
			return null;
	}

	@Override
	public void leave(AuthToken authToken, String gameID) {
		// TODO Auto-generated method stub
		if (ServerModel.getSingleton().checkAuthToken(authToken))
			ServerModel.getSingleton().leave(gameID,
					ServerModel.getSingleton().getUserFromAuthToken(authToken));
	}

	@Override
	public Object openGames() {
		// TODO Auto-generated method stub
		ArrayList<String> ids = (ArrayList<String>) ServerModel.getSingleton()
				.getOpenGames();
		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		for (int i = 0; i < ids.size(); i++) {
			games.add(ServerModel.getSingleton().getGameInfo(ids.get(i)));
		}
		return new GameList(games);
	}

	@Override
	public Object inProgressGames(AuthToken authToken) {
		// TODO Auto-generated method stub
		ArrayList<String> ids = (ArrayList<String>) ServerModel.getSingleton()
				.getUserFromAuthToken(authToken).getInProgressGames();
		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		for (int i = 0; i < ids.size(); i++) {
			games.add(ServerModel.getSingleton().getGameInfo(ids.get(i)));
		}
		return new GameList(games);
	}

	@Override
	public Object unstartedGames(AuthToken authToken) {
		// TODO Auto-generated method stub
		ArrayList<String> ids = (ArrayList<String>) ServerModel.getSingleton()
				.getUserFromAuthToken(authToken).getUnstartedGames();
		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		for (int i = 0; i < ids.size(); i++) {
			games.add(ServerModel.getSingleton().getGameInfo(ids.get(i)));
		}
		return new GameList(games);
	}

	@Override
	public StartedGameResult startGame(String gameID) {
		// TODO Auto-generated method stub
		Object result = ServerModel.getSingleton().startGame(gameID);
		return (StartedGameResult) result;
	}

	@Override
	public void claimRoute(AuthToken authToken, String gameID, Route route) {
		// TODO IMPLEMENT THIS METHOD
		Game g = checkInGame(authToken, gameID);
		if (g == null)
			return;
		// get routes
		// "claim route for ServerModel"
		// "claim route for player"
		// call clientProxy for claim route

	}

	@Override
	public void drawTrainCard(AuthToken authToken, String gameID) {
		// TODO IMPLEMENT THIS METHOD
		Game g = checkInGame(authToken, gameID);
		if(g == null) return;
		// check if user is in game
		// get game and get card from faceup
		TrainCard trainCard = g.drawTrainCard();
		// remove from faceup
		// add card to position
		// call client proxy
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		ClientProxy proxy = new ClientProxy(playerID, gameID);
		proxy.updateTrainHand(trainCard);
		proxy.updateTrainDeckSize();
		for(Player id : g.getPlayerList()){
			if(!id.getPlayerID().equals(playerID)){
				proxy = new ClientProxy(id.getPlayerID(), gameID);
				proxy.updateEnemyTrainHand(playerID, );
				proxy.updateTrainDeckSize();
			}
		}
	}

	@Override
	public void drawDestinationCard(AuthToken authToken, String gameID) {
		// TODO IMPLEMENT THIS METHOD
		Game g = checkInGame(authToken, gameID);
		if(g == null) return;
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		// check if user in game
		// get game and draw 3 cards
		List<DestinationCard> cards = g.drawDestinationCards();
		ClientProxy proxy = new ClientProxy(playerID, gameID);
		proxy.showDestinationCardChoices(cards);
		proxy.updateDestinationDeckSize();
		for(Player id: g.getPlayerList()){
			if(!id.getPlayerID().equals(playerID)){
				proxy = new ClientProxy(id.getPlayerID(), gameID);
				proxy.updateEnemyDestinationHand(id.getPlayerID(), );
				proxy.updateDestinationDeckSize();
			}

		}
		// call proxy
	}

	@Override
	public void sendBackDestinations(AuthToken authToken, String gameID,
			List<DestinationCard> list) {
		// check if user in game
		Game g = checkInGame(authToken, gameID);
		if (g == null)
			return;
		// get game and return list of cards, shuffle
		// call proxy for each player
	}

	@Override
	public void drawFromFaceUp(AuthToken authToken, String gameID,
			int card) {
		// TODO IMPLEMENT THIS METHOD
		Game g = checkInGame(authToken, gameID);
		if(g == null) return;
		// check if user is in game
		// get game and get card from faceup
		TrainCard trainCard = g.drawFaceUpCard(card);
		// remove from faceup
		// add card to position
		List<TrainCard> list = g.getFaceUpCards();
		// call client proxy
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		ClientProxy proxy = new ClientProxy(playerID, gameID);
		proxy.updateFaceUp(list);
		proxy.updateTrainHand(trainCard);
		proxy.updateTrainDeckSize();
		for(Player id : g.getPlayerList()){
			if(!id.getPlayerID().equals(playerID)){
				proxy = new ClientProxy(id.getPlayerID(), gameID);
				proxy.updateFaceUp(list);
				proxy.updateEnemyTrainHand(playerID, );
				proxy.updateTrainDeckSize();
			}
		}
	}

	private Game checkInGame(AuthToken authToken, String gameID) {
		// check
		boolean inGame = false;
		User u = ServerModel.getSingleton().getUserFromAuthToken(authToken);
		for (String id : (String[]) ((ArrayList<String>) u.getInProgressGames())
				.toArray()) {
			if (id.equals(gameID))
				inGame = true;
		}
		if (!inGame)
			return null;
		// get game from ServerModel
		Game g = ServerModel.getSingleton().getGame(gameID);
		return g;
	}

	@Override
	public void sendChat(AuthToken authToken, ChatMessage chatMessage) {
		// TODO Auto-generated method stub

	}

}
