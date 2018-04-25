package model;


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
	public void landOnField(Player player, Player[] playerArray) {
		player.setPosition(10);
		player.setInPrison(1);
	}
}
