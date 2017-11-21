package Commands.InGameCommands;

import java.util.ArrayList;

import Common.ICommand;
import Model.PlayerInfo;


/**
 * Created by Antman 537 on 11/20/2017.
 */

public class EndgameCommand implements ICommand {
    
	 private ArrayList<PlayerInfo> playerList;
	 private String className = getClass().getName();

	 
	 public EndgameCommand(ArrayList<PlayerInfo> list) {
		 this.playerList=playerList;
	}

	
    @Override
    public Object execute() {
        return null;
    }
}
