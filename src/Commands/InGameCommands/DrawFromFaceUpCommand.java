package Commands.InGameCommands;

import Common.*;
import Model.*;
import Server.ServerFacade;

/**
 * Created by abram on 10/23/2017.
 */

public class DrawFromFaceUpCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;
    private int card;
    private String className=getClass().getName();




    @Override
    public Object execute()
    {
        ServerFacade.getSingleton().drawFromFaceUp(authToken, gameID, card);
        return null;
    }

}
