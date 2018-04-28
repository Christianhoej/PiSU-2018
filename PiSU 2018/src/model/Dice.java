package model;

/**
 * Contains methods for rolling the dice, as well as getters and setters for the faceValue of the dice. 
 * @author 
 *
 */
public class Dice {
		 
		private int faceValue;

		public int rollDice() {
			faceValue = (int)(Math.random()*6+1);
			return faceValue;
		}

		public int getFaceValue() {
			return faceValue;
		}
		
		public void setFaceValue(int newFaceValue) {
			faceValue = newFaceValue;
	}
}
