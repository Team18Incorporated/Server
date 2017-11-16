package Commands.InGameCommands;

import Common.*;
import Model.*;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateTrainHandCommand implements ICommand {

	private TrainCard card1;
    private String className=getClass().getName();


	@Override
	public Object execute() {
		return null;
	}

	public UpdateTrainHandCommand(TrainCard card1) {
		this.card1 = card1;
	}

	public TrainCard getCard1() {
		return card1;
	}

	public void setCard1(TrainCard card1) {
		this.card1 = card1;
	}



}
