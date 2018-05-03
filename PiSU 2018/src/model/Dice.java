package model;

/**
 * Contains methods for rolling the dice, as well as getters and setters for the faceValue of the dice. 
 * @author 
 *
 */
public class Dice {
		 
		private int faceValue1;
		private int faceValue2;
		private int[] faceValue = new int[2];

		public void rollDice() {
			faceValue[0] = (int)(Math.random()*6)+1;
			faceValue[1] = (int)(Math.random()*6)+1;
		}

		public int[] getFaceValue() {
			return faceValue;
		}
		
		public void setFaceValue(int[] faceValue) {
			this.faceValue = faceValue;
		}
		public static boolean isEqual(Dice[] dice) {
			if (dice[0].getFaceValue()== dice[1].getFaceValue())
				return true;
			else
				return false;
		}
}
