package Commands.InGameCommands;

import java.util.List;

import Common.*;
import Model.*;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateDestinationHandCommand implements ICommand {

    private List<DestinationCard> list;
    private String className=getClass().getName();



    @Override
    public Object execute()
    {
    	return null;
    }

    public UpdateDestinationHandCommand(java.util.List<DestinationCard> list) {
        this.list = list;
    }

}
