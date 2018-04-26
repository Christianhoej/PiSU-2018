package dao;

import java.sql.SQLException; 
import java.util.ArrayList;
import model.Game;
import model.Player;

public class Test {
	static GameOAD g = new GameOAD();

	public static void main(String[] args) throws SQLException {
		//		Game game = new Game();
		//		Player p1 = new Player("Gunn");
		//		Player p2 = new Player("Gunn");
		//		Player p3 = new Player("Gunn");
		//		
		//		p1.setColour("Black");
		//		p2.setColour("Blue");
		//		p3.setColour("Brown");
		//	
		//		game.addPlayer(p1);
		//		game.addPlayer(p2);
		//		game.addPlayer(p3);
		//		
		//		g.createGame(game);

		ArrayList<Game> allGames = g.readAllGames();
		//		for(int i = 0; i<allGames.size(); i++) {
		//			System.out.println(allGames.get(i).getGameID()+ ", " + allGames.get(i).getGameDate() + ", " + allGames.get(i).getPlayerAmount());
		//		}

		Game ga = allGames.get(1);
		System.out.println("GameID: "+ga.getGameID());

		ArrayList<Player> allPlayers = g.readPlayers(ga);
		for(int i = 0; i<allPlayers.size(); i++) {
			System.out.println(allPlayers.get(i).getPlayerID()+ ", " + allPlayers.get(i).getName() + ", " + allPlayers.get(i).getColour());		
		}
		ga.setPlayers(allPlayers);
		System.out.println("PlayerSize: " + allPlayers.size());

		ga.setFields();

		ga.getFields().get(1).setOwner(allPlayers.get(0));
		ga.getFields().get(3).setOwner(allPlayers.get(1));
		ga.getFields().get(5).setOwner(allPlayers.get(2));
		ga.getPlayers().get(0).addOwnedProperties(1);
		ga.getPlayers().get(1).addOwnedProperties(3);
		ga.getPlayers().get(2).addOwnedProperties(5);
		System.out.println("Owner1: "+ga.getFields().get(1).getOwner().getPlayerID());
		System.out.println("Owner1: "+ ga.getFields().get(3).getOwner().getPlayerID());
		System.out.println("Owner1: "+ga.getFields().get(5).getOwner().getPlayerID());
		//		g.updateProperties(ga);




		System.out.println();
		g.readProperty(ga);
		System.out.println(ga.getFields().get(1).getOwner().getPlayerID());		

		ga.getPlayers().get(0).getAccount().updateCash(100000);
		ga.getPlayers().get(1).getAccount().updateCash(-1111);
		ga.getPlayers().get(2).getAccount().updateCash(1500);
		g.updatePlayer(ga);
		
		g.updateSaveDate(ga);


		//		System.out.println("GameID: "+game.getGameID());


		//		for (Player player: game.getPlayers()) {
		//			System.out.println("PlayerID: "+player.getPlayerID());
		//		}

	}
}
