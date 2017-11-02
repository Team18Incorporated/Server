package Commands.InGameCommands;

import Common.*;
import Model.*;

/**
 * Created by abram on 10/28/2017.
 */

public class UpdateChatHistoryCommand implements ICommand {

    private ChatHistory history;
    //private String gameID;


    @Override
    public Object execute()
    {
        return null;
    }

    public UpdateChatHistoryCommand(ChatHistory history /* , String gameID*/) {
        this.history = history;
       // this.gameID = gameID;
    }

}
