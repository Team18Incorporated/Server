package Commands;

import java.util.Date;
import java.util.List;

import Common.ICommand;

public class CommandList implements ICommand{
    private List<ICommand> list;
    private Date date;
    private int index;
    

    @Override
    public Object execute()
    {
       return null;
    }

    public List<ICommand> getList() {
        return list;
    }

    public void setList(List<ICommand> list) {
        this.list = list;
    }

    public CommandList(List<ICommand> list, Date date) {
        this.list = list;
        this.date=date;
    }
    
    public CommandList(List<ICommand> list, int index)
    {
    	this.list=list;
    	this.index=index;
    }

	public CommandList() {
		// TODO Auto-generated constructor stub
	}
}
