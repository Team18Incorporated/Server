package Model;

import java.util.ArrayList;
import java.util.Date;

import Common.*;
import Commands.CommandList;

public class PlayerCommandList {
	private ArrayList<DateCommand> commands;
	
	public void addCommand(ICommand command){
		commands.add(new DateCommand(command));
	}
	
	public CommandList getCommands(Date latest){
		ArrayList<ICommand> resultList = new ArrayList<ICommand>();
		for(DateCommand d : commands){
			if(d.checkAfter(latest))
				resultList.add(d.getCommand());
		}
		return new CommandList(resultList);
	}
	
	private class DateCommand{

		private ICommand command;
		private Date date;
		
		public DateCommand(ICommand command){
			date = new Date();
			this.command = command;
		}
		
		public boolean checkAfter(Date latest){
			if(date.after(latest))
				return true;
			return false;
		}
		
		public ICommand getCommand(){
			return command;
		}
		
	}
}
