package Commands;

import java.util.List;

import Common.ICommand;
import Model.DestinationCard;

public class ShowDestinationChoicesCommand implements ICommand {
    private List<DestinationCard> list;
    private String className=getClass().getName();
    

    @Override
    public Object execute()
    {
        return null;
    }

    public ShowDestinationChoicesCommand(List<DestinationCard> list) {
        this.list = list;
    }

    public List<DestinationCard> getList() {
        return list;
    }

    public void setList(List<DestinationCard> list) {
        this.list = list;
    }
}