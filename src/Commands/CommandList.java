package Commands;

import java.util.List;

import Common.ICommand;

public class CommandList implements ICommand{
    private List<ICommand> list;

    

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

    public CommandList(List<ICommand> list) {
        this.list = list;
    }
}
