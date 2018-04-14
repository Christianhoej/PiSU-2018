package Test;

import Test.Player;
import board.Gameboard;
/**
 * 
 * @author Christian, Yoss, Gunn
 *
 */
public class GoToPrison extends Fields{

	public GoToPrison(int fieldNumber, String fieldName) {
		super(fieldNumber, fieldName);
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void landOnField(Player player) {
		player.setPosition(10);
		player.setInPrison(1);

	}

}
