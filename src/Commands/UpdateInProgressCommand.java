package Commands;

import Common.ICommand;
import Model.*;
import Server.ServerFacade;

public class UpdateInProgressCommand implements ICommand{
	
	AuthToken token;

	public UpdateInProgressCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return ServerFacade.getSingleton().inProgressGames(token);
	}

}
