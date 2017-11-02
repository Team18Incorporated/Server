package Commands.InGameCommands;

import Common.*;
import Model.*;
import Server.ServerFacade;

/**
 * Created by abram on 10/20/2017.
 */

public class DrawDestinationCardCommand implements ICommand{

    private AuthToken authToken;
    private String gameID;



    @Override
    public Object execute()
    {
        ServerFacade.getSingleton().drawDestinationCard(authToken, gameID);
        return null;
    }

}
