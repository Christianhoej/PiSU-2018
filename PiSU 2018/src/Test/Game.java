package Test;

public class Game {
	
	Winner winner = new Winner();
	Player [] player;
	
	private void loadGame() {
		//Kald til databaser om at loade
	}
	public void runGame() {
		setUpGame();
		
		while (winner.testIfWinner(player) == false){
			// hvor mange spillere skal spille?
			
			// Opret spillere 
		} 
		
	}
	private void setUpGame() {
		//if chose to load game
			loadGame();
		
	}
	
	
}
