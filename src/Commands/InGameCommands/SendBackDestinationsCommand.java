package Commands.InGameCommands;

import java.util.List;

import Common.*;
import Model.*;
import Server.ServerFacade;

/**
 * Created by abram on 10/23/2017.
 */

public class SendBackDestinationsCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;
    private List<DestinationCard> list;
    private String className=getClass().getName();



    @Override
    public Object execute()
    {
        ServerFacade.getSingleton().sendBackDestinations(authToken, gameID, list);
        return null;
    }


    
}
