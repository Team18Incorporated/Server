package Commands.InGameCommands;

import Common.*;
import Model.*;
import Server.ServerFacade;


/**
 * Created by abram on 10/20/2017.
 */

public class ClaimRouteCommand implements ICommand {

    private AuthToken authToken;
    private String gameID;
    private String playerID;
    private Route route;
    private String className=getClass().getName();




    @Override
    public Object execute() {
        ServerFacade.getSingleton().claimRoute(authToken, gameID, route);
		return null;
    }
    
    public ClaimRouteCommand(String gameID, String playerID, Route route){
    	this.gameID = gameID;
    	this.playerID = playerID;
    	this.route = route;
    }

}