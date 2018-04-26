package model;

/**@au
 * 
 */
import gui_main.GUI;

public class Property extends Fields {

	protected boolean forSale = true;
	protected int price;
	protected int rent;
	protected boolean mortage;
	protected int mortagePrice;
	protected Player owner;
	private GUI gui;
	protected int houses;
	String[] guiMessages = Txt.file("GameMessages.txt");


	public Property(int fieldNumber) {
		super(fieldNumber);
	}

	public int getFieldNumber() {
		return fieldNumber;
	}
	
	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public Player getOwner() {
		return owner;
	}

	public void setMortage(boolean mortage) {
		this.mortage = mortage;
	}

	public boolean getMortage() {
		return mortage;
	}

	public void setMortagePrice(int mortagePrice) {
		this.mortagePrice = mortagePrice;
	}

	public int getMortagePrice () {
		return mortagePrice;
	}

	public void setOwner(Player player) {
		this.owner = player;
		setForSale(false);
	}
	public String getFieldName() {
		return fieldName;
	}
	
	public void setHouses(int houses) {
		this.houses = houses;
	}
	
	public int getHouses() {
		return houses;
	}

	@Override
	public String toString(){
		return guiMessages[3] + fieldName;
	}
	/**
	 * Method for when a property is up for auction.
	 * @param player
	 * @param playerArray
	 */
	public void auction(Game game) {
		int currentPlayer = -1;
		gui.showMessage(getFieldName() + guiMessages[4]);	
		for (int i=0; i<game.getPlayers().size(); i++) {
			if (game.getCurrentPlayer().equals(game.getPlayers().get(i))) {
				currentPlayer=i;
			}
		}
		int currentBid=100;
		//New array with the player originally landing on the field, as the last. 
		Player[] auctionArray = new Player[game.getPlayers().size()];
		for(int i = currentPlayer+1; i<game.getPlayers().size();i++) {
			auctionArray[i]= game.getPlayers().get(i);
		}
		for(int i=0; i<=currentPlayer; i++) {
			auctionArray[i] = game.getPlayers().get(i);
		}
		//The amount of player withdrawn from the auction. 
		int playersOut=0;
		//index at the highest bidder at the current time. 
		int auctionWinner=-1;
		//Running as long as there are at least 2 players left
		while (playersOut<auctionArray.length-1) {
			for (int i=0; i<auctionArray.length; i++) {
				if (auctionArray[i]==null) {
					continue;
				}
				//Get user input, of the amount of which the player will bid over the current bid
				int bidOver = gui.getUserInteger(auctionArray[i].getName()+ guiMessages[5] + currentBid, 0, auctionArray[i].getAccount().getCash()-currentBid);
				//If the player bids 0, he is removed from the auction.
				if (bidOver==0) {
					auctionArray[i]=null;
					playersOut++;
				}
				//The player with the current highest bid, is set as winner, until the next bid.
				else {
					auctionWinner=i;
					currentBid += bidOver;
				}
			}
		}
		//Updates cash, assets and owned properties of the winner.
		//Updates the owner of the field. 
		if(auctionWinner != -1) {
			auctionArray[auctionWinner].getAccount().updateCash(-currentBid);
			auctionArray[auctionWinner].getAccount().updateAssetValue(price);
			setOwner(auctionArray[auctionWinner]);
			auctionArray[auctionWinner].addOwnedProperties(fieldNumber);
			gui.showMessage(auctionArray[auctionWinner].getName() + guiMessages[6] + currentBid);
		}
		else {
			gui.showMessage(guiMessages[8]);
		}
	}

	@Override
	public void landOnField(Game game) { 
	}

}

