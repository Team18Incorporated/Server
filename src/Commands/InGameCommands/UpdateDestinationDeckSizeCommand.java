package Commands.InGameCommands;

import Common.*;
import Model.*;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateDestinationDeckSizeCommand implements ICommand {
    private int size;
    private String className=getClass().getName();


    @Override
    public Object execute()
    {
        return null;
    }

    public UpdateDestinationDeckSizeCommand(int size) {
        this.size = size;
    }

}
