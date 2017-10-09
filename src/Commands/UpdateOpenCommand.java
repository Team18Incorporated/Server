package Commands;

import Common.ICommand;
import Server.ServerFacade;

public class UpdateOpenCommand implements ICommand{

	public UpdateOpenCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return ServerFacade.getSingleton().openGames();
	}

}
