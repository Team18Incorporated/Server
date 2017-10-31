package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.ChatHistory;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/28/2017.
 */

public class UpdateChatHistoryCommand implements ICommand {

    private ChatHistory history;
    //private String gameID;

    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateChatHistory(history);
    }

    public UpdateChatHistoryCommand(ChatHistory history /* , String gameID*/) {
        this.history = history;
       // this.gameID = gameID;
    }

    public ChatHistory getHistory() {
        return history;
    }

    public void setHistory(ChatHistory history) {
        this.history = history;
    }

//    public String getGameID() {
//        return gameID;
//    }
//
//    public void setGameID(String gameID) {
//        this.gameID = gameID;
//    }
}
