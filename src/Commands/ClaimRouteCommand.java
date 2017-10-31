package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.Route;

/**
 * Created by abram on 10/20/2017.
 */

public class ClaimRouteCommand implements ICommand {

    private AuthToken authToken;
    private String gameID;
    private String playerID;
    private Route route;

    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    public ClaimRouteCommand(AuthToken authToken, String gameID, String playerID, Route route) {
        this.authToken = authToken;
        this.gameID = gameID;
        this.playerID = playerID;
        this.route = route;
    }

    public ClaimRouteCommand(AuthToken authToken, String gameID, Route route){
        this.authToken = authToken;
        this.gameID = gameID;
        this.route = route;
    }

    //To be implemented differently on the server.
    @Override
    public void execute() {
        ClientFacade.getClientFacade().claimRoute(gameID, playerID, route);
    }

    @Override
    public String toString()
    {
        String name = ClientFacade.getClientFacade().findPlayerName(playerID);
        return name + " claimed route: " + route.getCity1().getCityName() + ", " + route.getCity2() + ".";
    }


    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}