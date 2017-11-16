package Commands.InGameCommands;

import Common.*;
import Model.*;
import Server.ServerFacade;

/**
 * Created by abram on 10/20/2017.
 */

public class DrawTrainCardCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;
    private String className=getClass().getName();




    @Override
    public Object execute()
    {
        ServerFacade.getSingleton().drawTrainCard(authToken, gameID);
        return null;
    }

}
