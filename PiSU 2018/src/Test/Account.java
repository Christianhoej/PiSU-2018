package Test;
/**
 * @author Christian
 *Contains methods and attributes for the players account.
 *This includes the players amount of cash and assets.
 *
 *The class will possibly contain the method for deciding whether the player is broke or not. 
 *
 */
public class Account {
	private int cash=30000;
	private int assets=0;
	
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
