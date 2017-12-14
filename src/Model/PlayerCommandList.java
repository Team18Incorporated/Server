package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import Common.*;
import Commands.CommandList;

public class PlayerCommandList implements Serializable{
	private ArrayList<DateCommand> commands= new ArrayList<>();
	
	public void addCommand(ICommand command){
		int index;
		if(commands.size()<1)
		{
			index=0;
		}
		else
		{
			index=commands.get(commands.size()-1).getIndex()+1;
		}
			
		commands.add(new DateCommand(command, index));
	}
	
	public CommandList getCommands(int index){//Date latest){
		ArrayList<ICommand> resultList = new ArrayList<ICommand>();
		ArrayList<DateCommand> toDelete = new ArrayList<>();
		//Date late = latest;
		int returnIndex = index;
		for(DateCommand d : commands){
			if(d.checkAfter(index))
				resultList.add(d.getCommand());
			/*else
				toDelete.add(d);*/
			if(returnIndex<d.getIndex())//late.before(d.getDate()))
			{
				returnIndex=d.getIndex();
				//late = d.getDate();
			}
		}
		/*for(DateCommand d : toDelete)
		{
			commands.remove(d);
		}*/
		return new CommandList(resultList, returnIndex); //late);
	}
	
	private class DateCommand implements Serializable{

		private ICommand command;
		private Date date;
		private int commandIndex;
		
		public DateCommand(ICommand command, int index){
			date = new Date();
			commandIndex=index;
			this.command = command;
		}
		
		public Date getDate() {
			// TODO Auto-generated method stub
			return date;
		}
		
		public int getIndex()
		{
			return commandIndex;
		}

		public boolean checkAfter(int index){//Date latest){
			/*if(date.equals(latest))
				return false;
			else if(date.after(latest))
				return true;
			return false;*/
			
			
			if(index>=commandIndex)
			{
				return false;
			}
			else if(index<commandIndex)
			{
				return true;
			}
			else	
				return false;
		}
		
		public ICommand getCommand(){
			return command;
		}
		
	}
}
