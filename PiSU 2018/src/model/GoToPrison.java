package model;

import controller.GameController;

/**
 * 
 * @author Christian, Yoss, Gunn
 *
 */
public class GoToPrison extends Fields{

	public GoToPrison(int fieldNumber) {
		super(fieldNumber);
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void landOnField(GameController gameController) {
		gameController.getGame().getCurrentPlayer().setPosition(10);
		gameController.getGame().getCurrentPlayer().setInPrison(1);
	}

}
