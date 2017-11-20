package edu.byu.cs.team18.tickettoride.Common.Commands;

import edu.byu.cs.team18.tickettoride.ClientFacade;

/**
 * Created by Antman 537 on 11/18/2017.
 */

public class SetPenaltiesCommand implements ICommand {
    private String playerID;
    private int penalties;

    public SetPenaltiesCommand(String idIn, int penaltiesIn){
        playerID = idIn;
        penalties = penaltiesIn;
    }

    @Override
    public void execute() {
        ClientFacade.getClientFacade().setPlayerPenalties(playerID,penalties);
    }
}
