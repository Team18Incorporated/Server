package Commands;

import java.util.List;

import Common.ICommand;
import Model.TrainCard;

public class UpdateFaceUpCommand implements ICommand {
    private List<TrainCard> list;

    

    //This command will execute on both the client and the server. This is its implementation on the client side.
    @Override
    public Object execute()
    {
        return null;
    }


}
