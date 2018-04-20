package test;
import board.Gameboard;
import board.TestBoard;
import gui_codebehind.GUI_BoardController;
import gui_main.GUI;
import model.Fields;
import model.Player;

import java.util.Arrays;
import java.util.Scanner;

public class PropertyTest extends Fields {

	protected boolean forSale=true;
	protected int price;
	protected int rent;
	protected boolean mortage;
	protected int mortagePrice;
	protected Player owner;
	protected Player player;
	private GUI gui;


	public PropertyTest(int fieldNumber, String fieldName) {
		super(fieldNumber, fieldName);
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
	}
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public String toString(){
		return "Du har landet på " + fieldName;
	}
	/**
	 * Method for when a property is up for auction.
	 * @param player
	 * @param playerArray
	 */
	public void auction(Player player, Player [] playerArray) {
		Scanner scan = new Scanner(System.in);
		
		int currentPlayer = -1;
		//gui.showMessage(getFieldName() + "er sat på auktion!");	
		System.out.println(getFieldName() + " er sat på auktion!");	
		for (int i=0; i<playerArray.length; i++) {
			if (player.equals(playerArray[i])) {
				currentPlayer=i;
			}
		}
		int currentBid=100;
		//New array with the player originally landing on the field, as the last. 
		Player[] auctionArray = new Player[playerArray.length];
		for(int i = currentPlayer+1; i<playerArray.length; i++) {
			auctionArray[i-(currentPlayer+1)]=playerArray[i];
		}
		for(int i=0; i<currentPlayer; i++) {
			auctionArray[i+(playerArray.length-currentPlayer-1)]=playerArray[i];
		}
		auctionArray[playerArray.length-1] = playerArray[currentPlayer];
		
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
//				int bidOver = gui.getUserInteger(auctionArray[i].getName()+ " hvor meget vil du bydde over " + currentBid, 0, auctionArray[i].getAccount().getCash()-currentBid);
				System.out.println(auctionArray[i].getName() + " hvor meget vil du bydde over " + currentBid+ ". Du kan vælge et tal mellem 0 og " + (auctionArray[i].getAccount().getCash()-currentBid));
				int bidOver = scan.nextInt();
				//If the player bids 0, he is removed from the auction.
				if (bidOver==0) {
					auctionArray[i]=null;
					playersOut++;
					if(playersOut>=auctionArray.length-1) {
						break;
					}
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
//			gui.showMessage(auctionArray[auctionWinner].getName() + " har købt grunden for " + currentBid);
			System.out.println(auctionArray[auctionWinner].getName() + " har købt grunden for " + currentBid);
		}
		else {
//			gui.showMessage("Ingen har valgt at bydde på grunden, spillet fortsættes.");
			System.out.println("Ingen har valgt at bydde på grunden, spillet fortsættes.");
		}
	}


	@Override
	public void landOnField(Player player, Player[] playerArray) {
		Scanner scan = new Scanner(System.in);
		//If the property is for sale
		if (forSale) {
			//Vil spilleren købe den ellers skal den sættes på auktion 

//			String playerChoice = gui.getUserSelection(player.getName()+ " vil du købe " + getFieldName() + " for " + price, "Ja", "Nej");

			System.out.println(player.getName()+ " vil du købe " + getFieldName() + " for " + price + ". Skriv Ja eller Nei: ");

			String playerChoice = scan.next();
			
			if (playerChoice.equalsIgnoreCase("ja")) {
				setForSale(false);
				setOwner(player);
				player.getAccount().updateCash(-price);
				player.addOwnedProperties(fieldNumber);
			}
			else auction(player, playerArray);

			//Færdiggøres
		}
		//Hvis grunden ikke er til salg
		//Spilleren kan, når han lander på grunden:
		//Betale leje 
		//Ikke betale leje (Hvis ejeren er i fængsel, eller ved pansætning
		//Sætte ejendommen på auktion. 
		else if (forSale==false) {
			if (owner.equals(player)) {
//				gui.showMessage(toString() + "Du er selv ejer af dette felt, og skal ikke betale noget.");
				System.out.println(toString() + "Du er selv ejer af dette felt, og skal ikke betale noget.");
			}
			else if (owner.getInPrison()!= 0) { 
//				gui.showMessage(toString() + "Ejeren er i fængsel, du slipper denne gang.");
				System.out.println(toString() + "Ejeren er i fængsel, du slipper denne gang.");
			}
			else if (getMortage()) {
//				gui.showMessage(toString() + "Grunden er pantsat, du slipper denne gang.");
				System.out.println(toString() + "Grunden er pantsat, du slipper denne gang.");
			}
			else {
//				gui.showMessage("Du er landet på " + owner +"'s ejendom og skal betale " + price);
				System.out.println("Du er landet på " + owner +"'s ejendom og skal betale " + price);
				getOwner().getAccount().updateCash(price);
				player.getAccount().updateCash(-price);
				//Implementer: Tjek om spiller er broke. 
			}
		}
	}
}
