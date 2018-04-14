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

	public void moveToPrison (Player player) {
		player.setPosition(10);
		player.setInPrison(true);
	}
	
}
