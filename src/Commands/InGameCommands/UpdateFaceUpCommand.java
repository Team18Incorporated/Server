package Commands.InGameCommands;

import java.util.List;

import Common.*;
import Model.*;
/**
 * Created by abram on 10/22/2017.
 */

public class UpdateFaceUpCommand implements ICommand {
    private List<TrainCard> list;
    private String className=getClass().getName();


    public UpdateFaceUpCommand(List<TrainCard> list) {
        this.list = list;
    }



    //This command will execute on both the client and the server. This is its implementation on the client side.
    @Override
    public Object execute()
    {
        return null;
    }

    public String toString()
    {
        return "Changes made to Face Up Cards";
    }


}
