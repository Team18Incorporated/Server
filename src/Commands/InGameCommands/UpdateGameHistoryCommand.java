package Commands.InGameCommands;

import java.util.Date;

import Common.ICommand;
import Model.AuthToken;
import Server.ServerFacade;

public class UpdateGameHistoryCommand implements ICommand{
	private AuthToken authToken;
	private String gameID;
	//private Date date;
	private int index;
    private String className=getClass().getName();

	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return ServerFacade.getSingleton().getHistory(authToken, gameID, index);
	}

}
