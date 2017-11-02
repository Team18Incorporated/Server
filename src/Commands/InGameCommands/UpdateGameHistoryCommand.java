package Commands.InGameCommands;

import java.util.Date;

import Common.ICommand;
import Model.AuthToken;
import Server.ServerFacade;

public class UpdateGameHistoryCommand implements ICommand{
	AuthToken authToken;
	String gameID;
	Date date;
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return ServerFacade.getSingleton().getHistory(authToken, gameID, date);
	}

}
