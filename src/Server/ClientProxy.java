package Server;

import java.util.List;

import Common.IClient;
import Common.ICommand;
import Model.*;
import Commands.InGameCommands.*;

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
		addCommand(new UpdateTrainDeckSizeCommand(size));
	}

	@Override
	public void updateDestinationDeckSize(int size) {
		// TODO Auto-generated method stub
		addCommand(new UpdateDestinationDeckSizeCommand(size));
	}

	@Override
	public void updateScore(int points) {
		// TODO Auto-generated method stub
		addCommand(new UpdateScoreCommand(points));
	}

	@Override
	public void claimRoute(String gameID, String playerID, Route route) {
		// TODO Auto-generated method stub
		addCommand(new ClaimRouteCommand(gameID, playerID, route));
	}

	@Override
	public void updateTrainHand(TrainCard card1) {
		// TODO Auto-generated method stub
		addCommand(new UpdateTrainHandCommand(card1));
	}

	@Override
	public void updateDestinationHand(List<DestinationCard> list) {
		// TODO Auto-generated method stub
		addCommand(new UpdateDestinationHandCommand(list));
	}

	@Override
	public void updateEnemyTrainHand(String playerID, int size) {
		// TODO Auto-generated method stub
		addCommand(new UpdateEnemyTrainHandCommand(playerID, size));
	}

	@Override
	public void updateEnemyDestinationHand(String playerID, int size) {
		// TODO Auto-generated method stub
		addCommand(new UpdateEnemyDestinationHandCommand(playerID, size));
	}

	@Override
	public void updateEnemyScore(String playerID, int score) {
		// TODO Auto-generated method stub
		addCommand(new UpdateEnemyScoreCommand(playerID, score));
	}

	@Override
	public void showDestinationCardChoices(List<DestinationCard> list) {
		// TODO Auto-generated method stub
		addCommand(new ShowDestinationChoicesCommand(list));
	}

	@Override
	public void updateFaceUp(List<TrainCard> list) {
		// TODO Auto-generated method stub
		addCommand(new UpdateFaceUpCommand(list));
	}

	@Override
	public void updateChatHistory(ChatHistory chatHistory) {
		// TODO Auto-generated method stub
		addCommand(new UpdateChatHistoryCommand(chatHistory));
	}
	
	private void addCommand(ICommand command){
		ServerModel.getSingleton().getGame(gameID).getPlayer(playerID).addCommand(command);
	}

}
