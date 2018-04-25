package model;

public class NeutralField extends Fields{

	public NeutralField(int fieldNumber) {
		super(fieldNumber);
	}

	@Override
	public void landOnField(Player player, Player[] playerArray) {
	}

	@Override
	public String toString() {
		if(fieldNumber == 10) {
			return "Du er på besøg";
		}
		else if (fieldNumber == 20) {
			return "Gratis parkering, du sidder over";
		}
		else {
			return "Du er landet på start, modtag 4000kr.";
		}
	}
}