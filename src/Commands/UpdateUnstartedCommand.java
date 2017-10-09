package Commands;

import Common.ICommand;
import Model.AuthToken;
import Server.ServerFacade;

public class UpdateUnstartedCommand implements ICommand{
	
	AuthToken authToken;

	public UpdateUnstartedCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return ServerFacade.getSingleton().unstartedGames(authToken);
	}

}
