package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateTrainHandCommand implements ICommand {

    private TrainCard card1;
    private TrainCard card2;


    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateTrainHand(card1, card2);
    }

    public UpdateTrainHandCommand(TrainCard card1, TrainCard card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    public TrainCard getCard1() {
        return card1;
    }

    public void setCard1(TrainCard card1) {
        this.card1 = card1;
    }

    public TrainCard getCard2() {
        return card2;
    }

    public void setCard2(TrainCard card2) {
        this.card2 = card2;
    }
}
