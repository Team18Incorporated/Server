package Server;

import java.util.List;

import Common.IClient;
import Model.ChatHistory;
import Model.DestinationCard;
import Model.Game;
import Model.GameInfo;
import Model.GameList;
import Model.Player;
import Model.Route;
import Model.TrainCard;
import Model.User;

public class ClientProxy implements IClient {
	
	private String gameID;
	private String playerID;
	
	public ClientProxy(String GameID, String PlayerID){
		this.gameID = GameID;
		this.playerID = PlayerID;
	}

	@Override
	public void updateJoinGameList(GameList gameList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCurrentGamesList(GameList gameList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePlayer(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGame(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGame(GameInfo gameInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTrainDeckSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDestinationDeckSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScore(int points) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void claimRoute(String gameID, String playerID, Route route) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTrainHand(TrainCard card1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDestinationHand(List<DestinationCard> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnemyTrainHand(String playerID, int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnemyDestinationHand(String playerID, int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnemyScore(String playerID, int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDestinationCardChoices(List<DestinationCard> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFaceUp(List<TrainCard> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChatHistory(ChatHistory chatHistory) {
		// TODO Auto-generated method stub
		
	}

}
