package model;

public class Player {

	private String name;
	private Account account;
	protected int position=0;
	private boolean broke;
	private int inPrison;
	private int[] ownedProperties = new int[32];
	private int playerID;
	private String colour;
	private boolean current;

	public Player(String name /*, int balance, int position, boolean broke, boolean winner, boolean inPrison*/) {
		this.name = name;
		this.account = new Account(30000);
		//		this.position = position;
		//		this.broke = broke;
		//		this.winner = winner;	
		//		this.inPrison = inPrison;
	}
	public String getName() {
		return name;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public String getColour() {
		return colour;
	}

	// Smartere at kalde account direkte, istedet for at lave "hjælpe metoder" til at gøre det samme
	// --> Undgå redundens
	public Account getAccount() {
		return account;
	}

	//	public int[] getAccount1() {
	//		int[] acc = new int[3];
	//		acc[0] = account.getCash();
	//		acc[1] = account.getAssetValue();
	//		acc[2] = account.getPrisonCard();
	//		return acc;
	//	}

	//	public void updateAccount(int cash, int assetValue, int prisonCard) {
	//	account.updateCash(cash);
	//	account.updateAssetValue(assetValue);
	//	account.updatePrisonCard(prisonCard);
	//}

	public void setPosition(int position) {
		if (this.position > position && inPrison <1)
	    account.updateCash(4000);
		
		this.position = position;
		
	}

	public int getPosition() {
		return position;
	}

	public boolean isBroke() {
		return broke;
	}

	public void setBroke(boolean broke) {
		this.broke = broke;
	}

	public int getTotalValue() {
		return account.getCash()+account.getAssetValue();
	}

	public int getInPrison() {
		return inPrison;
	}

	/**
	 * 
	 * @param inPrison - 	value, that determines if and how long a player has been in prison.
	 * 						0 - player is not in prison
	 * 						1 - player is in prison
	 * 						2 - player is in prison and has been there for one turn
	 * 						3 - player is in prison and has been there for two turns
	 * 						4 - player is in prison and has been there for three turns
	 * 
	 */
	public void setInPrison(int inPrison) {
		this.inPrison=inPrison;
	}

	public void addOwnedProperties(int fieldNumber) {
		ownedProperties[fieldNumber] = 1;
	}

	public void removeOwnedProperties(int fieldNumber) {
		ownedProperties[fieldNumber] = 0;
	}

	public int[] getOwnedProperties() {
		return ownedProperties;
	}
	
	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	public boolean getCurrent() {
		return current;
	}

}

//Delvist taget fra CDIO3 - not done - Yoss
