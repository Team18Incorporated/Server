import Common.AuthToken;
import Common.GameInfo;
import Common.IServer;
import Model.ServerModel;
import Model.User;

public class ServerFacade implements IServer{

	public ServerFacade() {
		
	}

	@Override
	public AuthToken userLogin(String user, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthToken registerUser(String user, String password) {
		// TODO Auto-generated method stub
		if(ServerModel.getSingleton().registerUser(new User(user, password)))
			return new AuthToken();
		return null;
	}

	@Override
	public GameInfo newGame(AuthToken authToken, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void join(AuthToken authToken, String gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leave(AuthToken authToken, String gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object openGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object inProgressGames(AuthToken authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object unstartedGames(AuthToken authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startGame(String gameID) {
		// TODO Auto-generated method stub
		
	}

}
