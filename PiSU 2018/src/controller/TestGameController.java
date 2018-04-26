package controller;

import board.Gameboard;
import gui_main.GUI;
import model.Game;

public class TestGameController {
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		GameController gameCon = new GameController(game);
		
		gameCon.createPlayers();
		
	}

}
