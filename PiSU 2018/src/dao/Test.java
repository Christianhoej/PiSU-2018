package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Game;
import model.Player;
import model.Property;

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
		System.out.println("PlayerSize: " + allPlayers.size());
		
		ga.getProperties().get(1).setOwner(allPlayers.get(0));
		ga.getProperties().get(3).setOwner(allPlayers.get(1));
		ga.getProperties().get(5).setOwner(allPlayers.get(2));
		g.updateProperties(ga);
		
		
		
		
		System.out.println();
		ArrayList<Property> allProperties = g.readProperty(ga);
		for(int i = 0; i<allProperties.size(); i++) {
			System.out.println(allProperties.get(i).getFieldName()+ ", " + allProperties.get(i).getMortage() + ", " + allProperties.get(i).getHouses());		
		}
		
		System.out.println(allProperties.size());
		
		
//		System.out.println("GameID: "+game.getGameID());
				
		
//		for (Player player: game.getPlayers()) {
//			System.out.println("PlayerID: "+player.getPlayerID());
//		}
		
	}
}
