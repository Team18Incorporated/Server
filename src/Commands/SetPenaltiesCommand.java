package Commands;

import Common.ICommand;


/**
 * Created by Antman 537 on 11/18/2017.
 */

public class SetPenaltiesCommand implements ICommand {
    private String playerID;
    private int penalties;

    public SetPenaltiesCommand(String idIn, int penaltiesIn){
        playerID = idIn;
        penalties = penaltiesIn;
    }

   

    @Override
    public Object execute() {
       return null;
    }
}
