package board;

import gui_main.GUI;

public class TestBoard {
    
	public static void main(String[] args) {
		Gameboard b = new Gameboard();
		b.makeBoard();
		GUI gui = new GUI(b.makeBoard());
		String antal = gui.getUserSelection("Hvor mange spillere skal i være?", "3","4", "5", "6");
	}
}
