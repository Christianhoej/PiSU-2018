package model;

public class Game {
	
	Winner winner = new Winner();
	Player [] player;
	
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
