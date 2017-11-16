package Commands.InGameCommands;

import Common.*;
import Model.*;
import Server.ServerFacade;

/**
 * Created by abram on 10/28/2017.
 */

public class SendChatCommand implements ICommand {

    private ChatMessage message;
    private AuthToken authToken;
    private String gameID;
    private String className=getClass().getName();



    @Override
    public Object execute() {
        ServerFacade.getSingleton().sendChat(authToken, message, gameID);
        return null;
    }

    public SendChatCommand(ChatMessage message, AuthToken token) {
        this.message = message;
        this.authToken = token;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
