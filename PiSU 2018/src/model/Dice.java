package model;

/**
 * Contains methods for rolling the dice, as well as getters and setters for the faceValue of the dice. 
 * @author 
 *
 */
public class Dice {
		 
		private int[] faceValue = new int[2];
		private boolean rolled = false;

		public void rollDice() {
//			faceValue[0] = 2;
//			faceValue[1] = 3;
			faceValue[0] = (int)(Math.random()*6)+1;
			faceValue[1] = (int)(Math.random()*6)+1;
		}

		public int[] getFaceValue() {
			return faceValue;
		}
		
		public void setFaceValue(int[] faceValue) {
			this.faceValue = faceValue;
		}
		
		public boolean isEqual(int[] faceValue) {
			if (faceValue[0]== faceValue[1])
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
			return (faceValue[0]+ faceValue[1]);
		}
}
