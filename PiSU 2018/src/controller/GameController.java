package controller;

import java.awt.Color;
import java.util.ArrayList;

import gui_main.GUI;
import model.Game;
import model.Player;

public class GameController {

	private Game game;
	private GUI gui;
	
	public GameController(Game game) {
		this.game = game;
	}
	
	
	public void createPlayers() {
		
		GUI gui = new GUI(); // SKAL SLETTES --> bruger den bare til at teste metoden
		int playerAmount = Integer.parseInt(gui.getUserSelection("Hvor mange spillere skal i være?", "3", "4", "5", "6"));
		ArrayList<String> color = new ArrayList<String>();
		color.add("Blå");
		color.add("Grøn");
		color.add("Gul");
		color.add("Rød");
		color.add("Sort");
		color.add("Hvid");
		
		for(int i = 0; i<playerAmount; i++) {
			Player player = new Player();
			player.setName(gui.getUserString("Indsast navnet på spiller " + (i+1)));
			
			String[] colorString = new String[color.size()];
			colorString = color.toArray(colorString); 
			String carColor = gui.getUserSelection("Hvilken farve bil vil du have?", colorString);
			switch(carColor) {
			case "Blå": player.setColour(Color.blue); break;
			case "Grøn": player.setColour(Color.blue); break;
			case "Gul": player.setColour(Color.blue); break;
			case "Rød": player.setColour(Color.blue); break;
			case "Sort": player.setColour(Color.blue); break;
			case "Hvid": player.setColour(Color.blue); break;
			}
			color.remove(carColor);
			player.setPosition(0);
		}
		
	}
	
	
	
	/**
	 * 
	 * @param player
	 * @param playerArray
	 * @param antalSpillere
	 * 
	 * 
	 */
	private void trade(Game game) {
		int currentPlayer=-1;
		for (int i=0; i<game.getPlayers().size(); i++) {
			if (game.getCurrentPlayer().equals(game.getPlayers().get(i))) {
				currentPlayer=i;
			}
		}
		Player[] otherPlayer = new Player[game.getPlayers().size()-1];
		for(int i=0; i<otherPlayer.length; i++) {
			otherPlayer[i] = game.getPlayers().get(i);
			if(otherPlayer[i].equals(game.getPlayers().get(currentPlayer))) {
				i--;
			}
		}


		int tradingPlayer = -1;
		while(tradingPlayer==-1) {
			String input = gui.getUserString("Indtast navnet på den spiller, du gerne vil handle med: ");
			for(int i=0; i<game.getPlayers().size(); i++) {
				if (input.equals(game.getPlayers().get(i).getName())) {
					if(game.getCurrentPlayer().getName().equals(input)) {
						gui.showMessage("Du kan ikke handle med dig selv. Prøv igen");
					}
					else {
						tradingPlayer = i;
						gui.showMessage(game.getCurrentPlayer().getName() + "vil gerne handle med " + game.getPlayers().get(tradingPlayer).getName());
						ArrayList<String> currentOwnedProp = new ArrayList<String>();
						ArrayList<String> tradeOwnedProp = new ArrayList<String>();
						for(int j=0; j<game.getCurrentPlayer().getOwnedProperties().length; j++) {
							if(game.getCurrentPlayer().getOwnedProperties()[j] == 1) {
								currentOwnedProp.add(game.getFields().get(j).getFieldName());
							}
							if(game.getPlayers().get(tradingPlayer).getOwnedProperties()[j] == 1) {
								tradeOwnedProp.add(game.getFields().get(j).getFieldName());
							}

						}
						String[] tradePlayerOwnedProp = new String[tradeOwnedProp.size()];
						tradePlayerOwnedProp = tradeOwnedProp.toArray(tradePlayerOwnedProp); 

						String[] currentPlayerOwnedProp = new String[currentOwnedProp.size()];
						currentPlayerOwnedProp = currentOwnedProp.toArray(currentPlayerOwnedProp); 

						if(currentPlayerOwnedProp.length>0) {
							String propertyCurrent = gui.getUserSelection("Hvilken grund vil du bytte ", currentPlayerOwnedProp);
						}
						else {
							
						}


					}

				}
			}
		}
		gui.showMessage("Du har ikke valgt et gyldigt navn. Prøv igen");
	}



	/**
	 * Method for when a property is up for auction.
	 * @param player
	 * @param playerArray
	 */
	public void auction(Game game) {
		int currentPlayer = -1;
		gui.showMessage(getFieldName() + " er sat på auktion!");	
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
				int bidOver = gui.getUserInteger(auctionArray[i].getName()+ " hvor meget vil du bydde over " + currentBid, 0, auctionArray[i].getAccount().getCash()-currentBid);
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
			gui.showMessage(auctionArray[auctionWinner].getName() + " har købt grunden for " + currentBid);
		}
		else {
			gui.showMessage("Ingen har valgt at bydde på grunden, spillet fortsættes.");
		}
	}

	
	public void pawn() {
		
	}
	
	private void loadGame() {
		//Kald til databaser om at loade
	}
	
	public void runGame() {
		setUpGame();

		while (winner.testIfWinner(player) == false){

		} 

	}
	private void setUpGame() {
		//if chose to load game
		loadGame();

	}

	
	
	
}
