package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by abram on 10/23/2017.
 */

public class DrawFromFaceUpCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;
    private TrainCard card;

    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        //To be implemented on the server side.
    }



    public DrawFromFaceUpCommand(AuthToken authToken, String gameID, TrainCard card) {
        this.card = card;
        this.gameID = gameID;
        this.authToken = authToken;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }


    public TrainCard getCard() {
        return card;
    }

    public void setCard(TrainCard card) {
        this.card = card;
    }

}
