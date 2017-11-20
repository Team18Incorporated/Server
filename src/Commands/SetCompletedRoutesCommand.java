package Commands;

import Common.ICommand;

/**
 * Created by Antman 537 on 11/18/2017.
 */

public class SetCompletedRoutesCommand implements ICommand {
    private String playerID;
    private int routes;

    public SetCompletedRoutesCommand(String idIn, int routesIn){
        playerID = idIn;
        routes = routesIn;
    }



    @Override
    public Object execute() {
        return null;
    	//ClientFacade.getClientFacade().setPlayerCompletedDestinations(playerID,routes);
    }
}
