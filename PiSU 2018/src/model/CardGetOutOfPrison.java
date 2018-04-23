package model;

public class CardGetOutOfPrison extends ChanceCard {
		
	public CardGetOutOfPrison(int cardNumber, String text, int action) {
		super(cardNumber, text, action);
		
	}
	public void updateCardGetOutOfJail(Player player) {
		player.getAccount().updatePrisonCard(1);
	}
	
}
