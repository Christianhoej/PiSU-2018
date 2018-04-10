package Test;

import Test.Account;

public class Player {

	private String name;
	private Account account;
	protected int position;
	private boolean broke;
	private boolean winner;
	private boolean inPrison;
	private int amount;
	private int[] ownedProperties = new int[32];

	public Player(String name/*, int balance, int position, boolean broke, boolean winner, boolean inPrison*/) {
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}

	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void subtractCash(int amount) {
		account.subtractCash(amount);
	}
	
	public void addCash(int amount) {
		account.addCash(amount);
	}
	
	public void subtractAssets(int amount) {
		account.subtractAssets(amount);
	}
	
	public void addAssets(int amount) {
		account.addAssets(amount);
	}
	
	public int getTotalValue() {
		return account.getCash()+account.getAssets();
	}
	
	public boolean isBroke() {
		return broke;
	}
	
	public void setBroke(boolean broke) {
		this.broke = broke;
	}

	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	public boolean isPrison() {
		return inPrison;
	}
	
	public void setInPrison(boolean inPrison) {
		this.inPrison=inPrison;
	}
	
	public void addOwnedProperties(int fieldNumber) {
		ownedProperties[fieldNumber] = 1;
	}
	
	
}

//Delvist taget fra CDIO3 - not done - Yoss
