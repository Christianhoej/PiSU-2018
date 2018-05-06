package model;

/**
 * Includes making the dice. Contains methods for rolling the dice, as well as
 * getters and setters for the faceValue of the dice.
 * 
 * @author Oliver
 *
 */
public class Dice {

	private int[] faceValue = new int[2];
	private boolean rolled = false;

	/**
	 * Rolls the dice
	 */
	public void rollDice() {
		faceValue[0] = (int) (Math.random() * 6) + 1;
		faceValue[1] = (int) (Math.random() * 6) + 1;
	}

	public int[] getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int[] faceValue) {
		this.faceValue = faceValue;
	}

	/**
	 * Checks if the dice has the same value
	 * 
	 * @param faceValue
	 * @return boolean
	 */
	public boolean isEqual(int[] faceValue) {
		if (faceValue[0] == faceValue[1])
			return true;
		else
			return false;
	}

	public boolean isRolled() {
		return rolled;
	}

	public void setRolled(boolean rolled) {
		this.rolled = rolled;
	}

	public int getSum() {
		return (faceValue[0] + faceValue[1]);
	}
}
