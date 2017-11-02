package Commands.InGameCommands;

import Common.*;
import Model.*;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateTrainDeckSizeCommand implements ICommand {

	private int size;

	@Override
	public Object execute() {
		return null;
	}

	public UpdateTrainDeckSizeCommand(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
