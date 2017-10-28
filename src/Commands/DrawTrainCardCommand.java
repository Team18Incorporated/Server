package Commands;

import Common.ICommand;
import Model.AuthToken;
import Server.ServerFacade;



public class DrawTrainCardCommand implements ICommand{

    private String gameID;
    private AuthToken token;

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }


    public CreateCommand(String gameID, AuthToken t)
    {
	this.gameID=gameID;
        token = t;
    }

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return ServerFacade.getSingleton().drawTrainCard(token, gameID);
		
	}

}
