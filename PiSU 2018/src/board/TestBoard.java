package board;

import gui_main.GUI;

public class TestBoard {
    
	public static void main(String[] args) {
		Gameboard b = new Gameboard();
		b.lavBr�t();
		GUI gui = new GUI(b.lavBr�t());
		String antal = gui.getUserSelection("Hvor mange spillere skal i v�re?", "2","3","4");
	}
}
