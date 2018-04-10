package Test;
/**
 * @author Christian++
 *Contains methods and attributes for the players account.
 *This includes the players amount of cash and assets.
 *
 *The class will possibly contain the method for deciding whether the player is broke or not. 
 *
 */

import Test.Player;
import Test.Property;
import java.util.Set;


public class Account {
	private int cash=30000;
	private int assets=0;
	private boolean broke;
	private Property property;
	private Property ownedProperties;
	
	/**
	 * Constructor for Account. 
	 * @param cash
	 */
	public Account (int cash) {
		this.cash=cash;
	}
	/**
	 * Add cash to the players account
	 * @param cash
	 */
	public void addCash(int cash) {
		this.cash+=cash;
	}
	/**
	 * Add assets to the players account.
	 * @param assets
	 */
	public void addAssets(int assets) {
		this.assets += assets;
	}	
	/**
	 * Subtracts cash from the players account. 
	 * @param cash
	 */
	public void subtractCash (int cash) {
		this.cash -= cash;
	}
	
	/**
	 * Subtracts assets from the players account.
	 * @param assets
	 */
	public void subtractAssets (int assets) {
		this.assets -= assets;
	}
	
	public boolean isBroke() {
		return broke;
	}
	
	public void setBroke(boolean broke) {
		if (cash<0){
		}
	}
	
	public void addOwnedProperty(Property property) {
		ownedProperties.addProperty(property);
		
	}
	
	public void removeOwnedProperty(Property property)
	
	
	public void pawn() {
		
	}
	
	
	/**
	 * Getters & setters
	 */
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getAssets() {
		return assets;
	}
	public void setAssets(int assets) {
		this.assets = assets;
	}	
}
