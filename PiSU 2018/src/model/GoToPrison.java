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
	public void landOnField(GameController gameController, Player player) {
		player.setPosition(10);
		player.setInPrison(1);
	}

}
