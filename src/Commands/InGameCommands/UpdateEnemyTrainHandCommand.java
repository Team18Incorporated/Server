package Commands.InGameCommands;

import Common.*;
import Model.*;
/**
 * Created by abram on 10/20/2017.
 */

public class UpdateEnemyTrainHandCommand implements ICommand {
    private String playerID;
    private int size;
    private String className=getClass().getName();



    @Override
    public Object execute()
    {
        return null;
    }



    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public UpdateEnemyTrainHandCommand(String playerID, int size) {
        this.playerID = playerID;
        this.size = size;
    }
}
