package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;


import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateEnemyDestinationHandCommand implements ICommand {

    private String playerID;
    private int size;

    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateEnemyDestinationHand(playerID,size);
    }

    @Override
    public String toString()
    {
        String name = ClientFacade.getClientFacade().findPlayerName(playerID);
        return name + " drew destinations! " + name + " has " + size + " destination cards!";
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public UpdateEnemyDestinationHandCommand(String playerID, int size) {
        this.playerID = playerID;
        this.size = size;
    }
}
