package edu.byu.cs.team18.tickettoride.Common.Commands;

import edu.byu.cs.team18.tickettoride.ClientFacade;

/**
 * Created by Antman 537 on 11/18/2017.
 */

public class LongestRouteCommand implements ICommand {
    private String playerID;

    public LongestRouteCommand(String idIn){
        playerID = idIn;
    }

    @Override
    public String getSuffix() {
        return "LongestRoute";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public void execute() {
        ClientFacade.getClientFacade().awardLongestRoute(playerID);
    }
}
