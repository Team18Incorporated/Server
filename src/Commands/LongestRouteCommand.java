package Commands;

import Common.ICommand;



/**
 * Created by Antman 537 on 11/18/2017.
 */

public class LongestRouteCommand implements ICommand {
    private String playerID;

    public LongestRouteCommand(String idIn){
        playerID = idIn;
    }

   

    @Override
    public Object execute() {
        return null;
    }
}
