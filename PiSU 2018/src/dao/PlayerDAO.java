package dao;

public class PlayerDAO {

	private int playerID;
	private int playerInfoID;
	private int carID;
	private int prison;
	private int getOutPrison;
	private int balance;
	private boolean broke;
	private boolean current;
	private String name;
	
	public PlayerDAO(int playerID, int playerInfoID, int carID, int prison, int getOutPrison, int balance, boolean broke,
			boolean current, String name) {
		this.playerID = playerID;
		this.playerInfoID = playerInfoID;
		this.carID = carID;
		this.prison = prison;
		this.getOutPrison = getOutPrison;
		this.balance = balance;
		this.broke = broke;
		this.current = current;
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getPlayerInfoID() {
		return playerInfoID;
	}
	public void setPlayerInfoID(int playerInfoID) {
		this.playerInfoID = playerInfoID;
	}
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
	public int getPrison() {
		return prison;
	}
	public void setPrison(int prison) {
		this.prison = prison;
	}
	public int getGetOutPrison() {
		return getOutPrison;
	}
	public void setGetOutPrison(int getOutPrison) {
		this.getOutPrison = getOutPrison;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean isBroke() {
		return broke;
	}
	public void setBroke(boolean broke) {
		this.broke = broke;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
