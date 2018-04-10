package Test;

public class Dice {
		 
		private int faceValue;
		
		public Dice(int value) {
			value = faceValue;
		}
		public int rollDice() {
			faceValue = (int)(Math.random()*6+1);
			return faceValue;
		}

		public int getFaceValue() {
			return faceValue;
		}
		
		public void setFaceValue(int newFaceValue) {
			faceValue = newFaceValue;
			//return faceValue; ?overflødig?
	}
}
//Taget fra CDIO 3 - Yoss