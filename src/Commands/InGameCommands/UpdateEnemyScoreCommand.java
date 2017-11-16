package Commands.InGameCommands;

import Common.*;
import Model.*;
/**
 * Created by abram on 10/23/2017.
 */

public class UpdateEnemyScoreCommand implements ICommand {
    private String playerID;
    private int points;
    private String className=getClass().getName();



    @Override
    public Object execute()
    {
        return null;
    }

    public UpdateEnemyScoreCommand(String playerID, int points) {
        this.playerID = playerID;
        this.points = points;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
