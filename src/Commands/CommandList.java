package edu.byu.cs.team18.tickettoride.Common.Commands;

import java.util.List;

/**
 * Created by abram on 10/20/2017.
 */

public class CommandList implements ICommand{
    private List<ICommand> list;

    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        for(int i = 0; i < list.size(); i++)
        {
            list.get(i).execute();
        }
    }

    public List<ICommand> getList() {
        return list;
    }

    public void setList(List<ICommand> list) {
        this.list = list;
    }

    public CommandList(List<ICommand> list) {
        this.list = list;
    }
}
