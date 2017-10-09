import java.util.UUID;

import Common.IServer;
import Model.AuthToken;
import Model.GameInfo;
import Model.ServerModel;
import Model.User;

public class ServerFacade implements IServer{

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
		return ServerModel.getSingleton().registerUser(new User(user, password, UUID.randomUUID().toString()));
	}

	@Override
	public GameInfo newGame(AuthToken authToken, String name) {
		// TODO Auto-generated method stub
		if(ServerModel.getSingleton().checkAuthToken(authToken)){
			return ServerModel.getSingleton().newGame(name, ServerModel.getSingleton().getUserFromAuthToken(authToken));
		}
		return null;
	}

	@Override
	public void join(AuthToken authToken, String gameID) {
		// TODO Auto-generated method stub
		if(ServerModel.getSingleton().checkAuthToken(authToken))
			ServerModel.getSingleton().join(gameID, ServerModel.getSingleton().getUserFromAuthToken(authToken));
	}

	@Override
	public void leave(AuthToken authToken, String gameID) {
		// TODO Auto-generated method stub
		if(ServerModel.getSingleton().checkAuthToken(authToken))
			ServerModel.getSingleton().leave(gameID, ServerModel.getSingleton().getUserFromAuthToken(authToken));
	}

	@Override
	public Object openGames() {
		// TODO Auto-generated method stub
		return ServerModel.getSingleton().getOpenGames();
	}

	@Override
	public Object inProgressGames(AuthToken authToken) {
		// TODO Auto-generated method stub
		return ServerModel.getSingleton().getUserFromAuthToken(authToken).getInProgressGames();
	}

	@Override
	public Object unstartedGames(AuthToken authToken) {
		// TODO Auto-generated method stub
		return ServerModel.getSingleton().getUserFromAuthToken(authToken).getUnstartedGames();
	}

	@Override
	public void startGame(String gameID) {
		// TODO Auto-generated method stub
		ServerModel.getSingleton().startGame(gameID);
	}

}
