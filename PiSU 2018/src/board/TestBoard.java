package board;

import java.awt.Color;

import controller.GameController;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;
import model.Game;
import model.Player;
import gui_codebehind.*;


public class TestBoard {
	

	public static void main(String[] args) {
		Game game = new Game();
		GameController gamecon = new GameController(game);
		gamecon.runGame();
		
		
		
//		Gameboard b = new Gameboard();
//		GUI gui = new GUI(b.makeBoard());
//		int playerAmount = gui.getUserSelection("Hvor mange spillere skal i være?", 1,2, 3, 4, 5, 6));
//		int playerAmount = Integer.parseInt(gui.getUserSelection("Hvor mange spillere skal i være?", "3","4", "5", "6", "7"));
//	
//		Player[] player = new Player[playerAmount];
//		String[] name = new String[playerAmount];
//		
//
//		GUI_Player[] s = new GUI_Player[playerAmount];
//		GUI_Car[] car = new GUI_Car[playerAmount];
//		Color [] color = {Color.blue, Color.red, Color.yellow, Color.black, Color.white, Color.green};
//		String[] playerColor = new String[playerAmount];
//		
////		 Tilføjer spillerne til spillet
//		for (int i=0; i<player.length; i++) {
//			name[i] = gui.getUserString("Indtast spiller " + (i+1) + "s navn");
//
//			if(name[i].equals("")){  // hvis der ikke er indtastet noget, sættes et standard spillernavn
//				name[i] = "Spiller " + (i+1);
//			}
//			
//			playerColor[i] = gui.getUserSelection("Hvilken farve vil du have?", "blå", "rød", "gul", "sort", "hvid", "grøn");
//			
//			car[i]= new GUI_Car(color[i], color[i], GUI_Car.Type.CAR, GUI_Car.Pattern.HORIZONTAL_GRADIANT); //Opretter bilerne
//
//
//			// Afgører startbalancen for spillerne og tilføjer dem til spillepladen
//			switch(playerAmount) {
//			case 2:
//				s[i] = new GUI_Player(name[i], 20, car[i]);
//				break;
//			case 3:
//				s[i] = new GUI_Player(name[i], 18, car[i]);
//				break;
//			case 4:
//				s[i] = new GUI_Player(name[i], 16, car[i]);
//				break;
//			}
//			gui.addPlayer(s[i]);
//			gui.getFields()[0].setCar(s[i], true);
//			player[i] = new Player();
//			player[i].setName(name[i]);
//	
//		}


	}
}
