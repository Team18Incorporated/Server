package Commands.InGameCommands;

import Common.*;
import Model.*;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateTrainHandCommand implements ICommand {

	private TrainCard card1;
	private TrainCard card2;

	@Override
	public Object execute() {
		return null;
	}

	public UpdateTrainHandCommand(TrainCard card1, TrainCard card2) {
		this.card1 = card1;
		this.card2 = card2;
	}

	public TrainCard getCard1() {
		return card1;
	}

	public void setCard1(TrainCard card1) {
		this.card1 = card1;
	}

	public TrainCard getCard2() {
		return card2;
	}

	public void setCard2(TrainCard card2) {
		this.card2 = card2;
	}

}
