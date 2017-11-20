package edu.byu.cs.team18.tickettoride.Common.Commands;

import edu.byu.cs.team18.tickettoride.ClientFacade;

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
    public void execute() {
        ClientFacade.getClientFacade().setPlayerCompletedDestinations(playerID,routes);
    }
}
