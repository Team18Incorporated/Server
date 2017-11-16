package Server;

import java.util.ArrayList;
import java.util.Date;
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
	public StartedGameResult startGame(String gameID, AuthToken token) {
		// TODO Auto-generated method stub
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(token).getID();
		return ServerModel.getSingleton().startGame(gameID, playerID);
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
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		// check if user is in game
		// get game and get card from faceup
		TrainCard trainCard = g.drawTrainCard();
		Player player = null;
		for(Player p : g.getPlayerList())
			if(p.getPlayerID() == playerID)
				player = p;
		ArrayList<TrainCard> cards = new ArrayList<TrainCard>();
		cards.add(trainCard);
		player.addCardstoHand(cards);
		// remove from faceup
		// add card to position
		// call client proxy
		ClientProxy proxy = new ClientProxy(gameID,playerID);
		proxy.updateTrainHand(trainCard);
		proxy.updateTrainDeckSize(-1);
		for(Player id : g.getPlayerList()){
			if(!id.getPlayerID().equals(playerID)){
				proxy = new ClientProxy(id.getPlayerID(), gameID);
				proxy.updateEnemyTrainHand(playerID, 1);
				proxy.updateTrainDeckSize(-1);
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
		Player player = null;
		for(Player p : g.getPlayerList())
			if(p.getPlayerID() == playerID)
				player = p;
		player.addDestinationCards((ArrayList<DestinationCard>) cards);
		ClientProxy proxy = new ClientProxy( gameID,playerID);
		proxy.showDestinationCardChoices(cards);
		proxy.updateDestinationDeckSize(-3);
		for(Player id: g.getPlayerList()){
			if(!id.getPlayerID().equals(playerID)){
				proxy = new ClientProxy(id.getPlayerID(), gameID);
				proxy.updateEnemyDestinationHand(id.getPlayerID(), 3);
				proxy.updateDestinationDeckSize(-3);
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
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		// check if user in game
		// get game and draw 3 cards
		g.discard(list);

		Player player = null;
		for(Player p : g.getPlayerList())
			if(p.getPlayerID() == playerID)
				player = p;
		player.discard((ArrayList<DestinationCard>)list);
		ClientProxy proxy = new ClientProxy(gameID,playerID);
		
		proxy.updateDestinationHand(player.getDestinationCards());
		for(Player id: g.getPlayerList()){
			if(!id.getPlayerID().equals(playerID)){
				proxy = new ClientProxy(id.getPlayerID(), gameID);
				proxy.updateEnemyDestinationHand(id.getPlayerID(), -3);
			}

		}
		// call proxy for each player
	}

	@Override
	public void drawFromFaceUp(AuthToken authToken, String gameID,
			int card) {
		// TODO IMPLEMENT THIS METHOD
		Game g = checkInGame(authToken, gameID);
		if(g == null) return;
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		// check if user is in game
		// get game and get card from faceup
		TrainCard trainCard = g.drawFaceUpCard(card);
		ArrayList<TrainCard> cards = new ArrayList<TrainCard>();
		cards.add(trainCard);
		// remove from faceup
		// add card to position
		List<TrainCard> list = g.getFaceUpCards();
		Player player = null;
		for(Player p : g.getPlayerList())
			if(p.getPlayerID() == playerID)
				player = p;
		player.addCardstoHand(cards);
		// call client proxy
		ClientProxy proxy = new ClientProxy(gameID,playerID);
		proxy.updateFaceUp(list);
		proxy.updateTrainHand(trainCard);
		proxy.updateTrainDeckSize(-1);
		for(Player id : g.getPlayerList()){
			if(!id.getPlayerID().equals(playerID)){
				proxy = new ClientProxy(id.getPlayerID(), gameID);
				proxy.updateFaceUp(list);
				proxy.updateEnemyTrainHand(playerID, 1);
				proxy.updateTrainDeckSize(-1);
			}
		}
	}

	private Game checkInGame(AuthToken authToken, String gameID) {
		// check
		boolean inGame = false;
		User u = ServerModel.getSingleton().getUserFromAuthToken(authToken);
		ArrayList<String>gameList = (ArrayList<String>) u.getInProgressGames();
		for (String id : gameList) {
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
	public void sendChat(AuthToken authToken, ChatMessage chatMessage, String gameID) {
		// TODO Auto-generated method stub
		Game g = checkInGame(authToken, gameID);
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		if(g == null) return;
		g.sendMsg(chatMessage);
		for(Player id : g.getPlayerList()){
			ClientProxy proxy = new ClientProxy( gameID, id.getPlayerID());
			proxy.updateChatHistory(g.getChatHistory());
		}
		System.out.println("past chat");
	}

	@Override
	public CommandList getHistory(AuthToken authToken, String gameID, Date date) {
		// TODO Auto-generated method stub
		Game g = checkInGame(authToken, gameID);
		String playerID = ServerModel.getSingleton().getUserFromAuthToken(authToken).getID();
		if(g == null) return new CommandList();
		Player player = null;
		for(Player p : g.getPlayerList())
			if(p.getPlayerID() == playerID)
				player = p;
		return player.getCommands(date);
	}

}
