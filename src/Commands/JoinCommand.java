package Commands;

import Common.ICommand;
import Model.AuthToken;
import Server.ServerFacade;

/**
 * Created by abram on 10/2/2017.
 */

public class JoinCommand implements ICommand{

    private String username;
    private String gameID;
    private AuthToken token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public JoinCommand(String un, String ID, AuthToken t)
    {
        username = un;
        gameID = ID;
        token = t;
    }

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		ServerFacade.getSingleton().join(token, gameID);
		return null;
	}

}
