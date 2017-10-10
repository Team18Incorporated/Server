package Commands;

import Common.ICommand;
import Model.AuthToken;
import Server.*;

public class LeaveCommand implements ICommand{
	
	AuthToken token;
	String gameID;

	public LeaveCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		ServerFacade.getSingleton().leave(token, gameID);
		return null;
	}

}
