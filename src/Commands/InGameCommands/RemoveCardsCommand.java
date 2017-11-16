package Commands.InGameCommands;

import java.util.ArrayList;

import Common.ICommand;
import Model.Route;
import Model.TrainCard;

/**
 * Created by Solomons on 10/31/2017.
 */

public class RemoveCardsCommand implements ICommand {
    private String color;
    private int length;
    private ArrayList<TrainCard> discard = new ArrayList<>();
    private String className;

    public  RemoveCardsCommand(Route route)
    {
        color= route.getColor().getColor();
        length=route.getLength();
        className=getClass().getName();
    }



    @Override
    public Object execute() {
        return null;
    }
}
