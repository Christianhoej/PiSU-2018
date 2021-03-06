package model;
/**
 * @author Christian


 * Contains methods and attributes for the players account.
 * This includes the players amount of cash and assets.
 *
 * The class will possibly contain the method for deciding whether the player is broke or not. 
 *
 */

import designpatterns.Subject;


public class Account extends Subject {
	private int cash;
	private int prisonCard = 0;
	private Player player;

	/**
	 * Constructor for Account. 
	 * @param cash - players amount of cash
	 */
	public Account (int cash) {
		this.cash = cash;
	}
	/**
	 * Add cash to the players account
	 * @param d
	 */
	public void updateCash(int d) {
		this.cash += d;
		notifyChange();
	}

	public int getCash() {
		return cash;
	}

	/**
	 * Updates the number of the players get out of prison cards
	 * @param prisonCard - Get out of prison cards
	 */
	public void updatePrisonCard(int prisonCard) {
		this.prisonCard += prisonCard;
		notifyChange();
	}
	
	public void setPrisonCard(int prisonCard) {
		this.prisonCard += prisonCard;
		notifyChange();
	}

	public int getPrisonCard() {
		return prisonCard;
	}
	
	public void setOwner(Player player) {
		this.player = player;
	}
	
	public Player getOwner() {
		return player;
	}
}
