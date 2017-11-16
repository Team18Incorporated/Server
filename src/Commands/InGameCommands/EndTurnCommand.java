package Commands.InGameCommands;

import Model.AuthToken;
import Common.ICommand;

/**
 * Created by abram on 11/15/2017.
 */

public class EndTurnCommand implements ICommand {

    private AuthToken token;
    private String gameID;
    private String playerID;
    private String className;



    @Override
    public Object execute() {
		return null;

    }

    public EndTurnCommand(AuthToken t, String g, String p)
    {
        token = t;
        gameID = g;
        playerID = p;
        className=getClass().getName();
    }

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

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
}
