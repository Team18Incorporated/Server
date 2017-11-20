package Commands.InGameCommands;

import Common.ICommand;


/**
 * Created by price on 11/18/2017.
 */

public class IncrementTurnCommand implements ICommand {

    private int turn;
    private String className=getClass().getName();

    
    public IncrementTurnCommand( int turn)
    {
    	this.turn= turn;
    }

    @Override
    public Object execute() {
        return null;
    }


}
