package model;

/**
 * Inheriting methods & attributes from the ChanceCard class.
 * Includes methods for chancecards of when a player must pay money. 
 * @author Christian, Yoss +
 *
 */
public class CardPay extends ChanceCard {

	private int amount;
	
	public CardPay(int cardNumber, String text, int amount) {
		super( cardNumber, text);
		this.amount = amount;
	}
	@Override
	public void performAction(Player player, Player[] playerArray) {
		//Skal ændres så den kan tage i mod forskellige inputs
		player.getAccount().updateCash(amount);
		
	}
	public int getAmount() {
		return amount;
	}
	
}
