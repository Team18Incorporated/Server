package Commands.InGameCommands;

import java.util.List;

import Common.*;
import Model.*;
/**
 * Created by abram on 10/22/2017.
 */

public class ShowDestinationChoicesCommand implements ICommand {
    private List<DestinationCard> list;
    private String className=getClass().getName();



    @Override
    public Object execute()
    {
        return null;
    }

    public ShowDestinationChoicesCommand(List<DestinationCard> list) {
        this.list = list;
    }

}
