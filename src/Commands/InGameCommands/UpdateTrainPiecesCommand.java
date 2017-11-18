package Commands.InGameCommands;

import Common.ICommand;




/**
 * Created by dasolomo on 11/18/17.
 */

public class UpdateTrainPiecesCommand implements ICommand {
    private String playerID;
    private int pieces;
    private String className;

    public UpdateTrainPiecesCommand(String playerID, int pieces) {
        this.playerID = playerID;
        this.pieces = pieces;
        className=getClass().getName();
    }



    @Override
    public Object execute() {
        return null;
    }
}
