package Test;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * includes methods for when a player must move his car, following a drawn chancecard. 
 * @author Christian ++
 *
 */

import Test.Player;

public class CardRecieve extends ChanceCard {

	private int amount;
	
	public void receiveMoney(Player player, int amount) {
		player.getAccount().addCash(amount);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
