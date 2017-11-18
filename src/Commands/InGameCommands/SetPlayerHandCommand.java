package Commands.InGameCommands;

import java.util.ArrayList;

import Common.ICommand;
import Model.TrainCard;



/**
 * Created by dasolomo on 11/18/17.
 */

public class SetPlayerHandCommand implements ICommand {

    private ArrayList<TrainCard> hand;
    private String className;

    public SetPlayerHandCommand(ArrayList<TrainCard> hand)
    {
        this.hand=hand;
        className=getClass().getName();

    }



    @Override
    public Object execute() {
        return null;
    }
}
