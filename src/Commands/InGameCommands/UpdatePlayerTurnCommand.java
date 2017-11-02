package Commands.InGameCommands;

import Common.*;
import Model.*;
/**
 * Created by Solomons on 10/31/2017.
 */

public class UpdatePlayerTurnCommand implements ICommand {
    private int playerIndex;

    public UpdatePlayerTurnCommand(int playerIndex) {
        this.playerIndex = playerIndex;
    }


    @Override
    public Object execute() {
        return null;
    }

}
