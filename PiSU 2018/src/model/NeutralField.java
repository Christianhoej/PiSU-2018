package model;
/**
 * 
 * @author Yoss
 *
 */
public class NeutralField extends Fields{
	
	String[] guiMessages = Txt.file("GameMessages.txt");
	public NeutralField(int fieldNumber) {
		super(fieldNumber);
	}
	

	@Override
	public void landOnField(Game game) {
	}

	@Override
	public String toString() {
		if(fieldNumber == 10) {
			return (guiMessages[0]);
		}
		else if (fieldNumber == 20) {
			return  (guiMessages[1]);
		}
		else {
			return (guiMessages[2]);
		}
	}
}
