package model;
/**

 * @author Christian
 * Contains methods and attributes for the players account.
 * This includes the players amount of cash and assets.
 *
 * The class will possibly contain the method for deciding whether the player is broke or not. 
 *
 */

import java.util.Set;


public class Account {
	private int cash;
	private int assetValue = 0;
	private int prisonCard = 0;

	/**
	 * Constructor for Account. 
	 * @param cash
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
	}


	public int getCash() {
		return cash;
	}

	/**
	 * Add assets to the players account.
	 * @param assets
	 */

	public void updateAssetValue(int assets) {
		this.assetValue += assets;
	}

	public int getAssetValue() {
		return assetValue;
	}

	public void updatePrisonCard(int prisonCard) {
		this.prisonCard += prisonCard;
	}

	public int getPrisonCard() {
		return prisonCard;
	}
}
