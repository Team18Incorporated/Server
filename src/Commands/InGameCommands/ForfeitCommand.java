package Commands.InGameCommands;

import Common.ICommand;
import Model.AuthToken;
import Server.ServerFacade;

/**
 * Created by Antman 537 on 11/27/2017.
 */

public class ForfeitCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;

    public ForfeitCommand(AuthToken tokenIn, String idIn){
        authToken = tokenIn;
        gameID = idIn;
    }


    @Override
    public Object execute() {
    	ServerFacade.getSingleton().forfeit(authToken, gameID);
    	return null;
    }
}
