package model;

import gui_main.GUI;

public class Game1 {
	
	Winner winner = new Winner();
	Player [] player;
	GUI gui;
	
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
	
	
	/**
	 * 
	 * @param player
	 * @param playerArray
	 * @param antalSpillere
	 * 
	 * @author Gunn, Christian
	 */
	private void trade(Player player, Player[] playerArray, int antalSpillere) {
		int currentPlayer=-1;
		for (int i=0; i<playerArray.length; i++) {
			if (player.equals(playerArray[i])) {
				currentPlayer=i;
			}
		}
		Player[] otherPlayer = new Player[playerArray.length-1];
		for(int i=0; i<otherPlayer.length; i++) {
			if(otherPlayer[i].equals(playerArray[currentPlayer])) {
				continue;
			}
			else {
				otherPlayer[i] = playerArray[i];
			}
		}
		
		
		int tradingPlayer = -1;
		while(tradingPlayer==-1) {
			String input = gui.getUserString("Indtast navnet på den spiller, du gerne vil handle med: ");
			for(int i=0; i<playerArray.length; i++) {
				if (input.equals(playerArray[i])) {
					if(player.getName().equals(input)) {
						gui.showMessage("Du kan ikke handle med dig selv. Prøv igen");
					}
					else {
						tradingPlayer = i;
					}
				}
			}
			gui.showMessage("Du har ikke valgt et gyldigt navn. Prøv igen");
		}
		
		
		// Ikke færdig
	}
	
	
}
