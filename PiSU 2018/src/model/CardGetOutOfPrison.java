package model;

public class CardGetOutOfPrison extends ChanceCard {
		
	public CardGetOutOfPrison(int cardNumber, String text) {
		super(cardNumber, text);
		
	}
	public void performAction(Player player) {
		player.getAccount().updatePrisonCard(1);
	}
	
}
