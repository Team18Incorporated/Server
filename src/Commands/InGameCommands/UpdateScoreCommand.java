package Commands.InGameCommands;

import Common.*;
import Model.*;
/**
 * Created by abram on 10/20/2017.
 */

public class UpdateScoreCommand implements ICommand {

    private int points;
    private String className=getClass().getName();



    @Override
    public Object execute()
    {
    	return null;
    }

    public UpdateScoreCommand(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
