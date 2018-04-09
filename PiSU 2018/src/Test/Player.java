package Test;

import Test.Account;

public class Player {

	private String name;
	private Account account;
	protected int position;
	private boolean broke;
	private boolean winner;
	private boolean inPrison;

	public Player(String name, int balance, int position, boolean broke, boolean winner, boolean inPrison) {
		this.name = name;
		this.account = new Account();
		this.position = position;
		this.broke = broke;
		this.winner = winner;	
		this.inPrison = inPrison;
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
	
	public boolean getBroke() {
		return broke;
	}

	public boolean getWinner() {
		return winner;
	}
	
	public boolean inPrison() {
		return inPrison;
	}
	
	public void setInPrison(boolean inPrison) {
		this.inPrison=inPrison;
	}
	
	
}

//Delvist taget fra CDIO3 - not done - Yoss
