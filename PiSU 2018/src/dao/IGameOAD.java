package dao;

import java.sql.SQLException;  
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Game;
import model.Player;
import model.Property;

public interface IGameOAD {

	
	//void createAll(String[] colour, String[] name);
	void updateAll();
	int readGame(Game game) throws SQLException;	
	void createPlayers(Game game) throws SQLException;
	ArrayList<Game> readAllGames() throws SQLException;
	ArrayList<Player> readPlayers(Game game) throws SQLException;
	ArrayList<Property> readProperty(Game game) throws SQLException;
	void createGame(Game game) throws SQLException;
	void createProperties(Game game) throws SQLException;
	
}
