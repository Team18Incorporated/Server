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
		Date late = latest;
		for(DateCommand d : commands){
			if(d.checkAfter(latest))
				resultList.add(d.getCommand());
			else
				commands.remove(d);
			if(late.before(d.getDate()))
				late = d.getDate();
		}
		return new CommandList(resultList, late);
	}
	
	private class DateCommand{

		private ICommand command;
		private Date date;
		
		public DateCommand(ICommand command){
			date = new Date();
			this.command = command;
		}
		
		public Date getDate() {
			// TODO Auto-generated method stub
			return date;
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