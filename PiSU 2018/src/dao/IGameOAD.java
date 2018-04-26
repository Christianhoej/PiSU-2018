package dao;

import java.sql.SQLException;   
import java.util.ArrayList;

import model.Fields;
import model.Game;
import model.Player;

public interface IGameOAD {

	
	//void createAll(String[] colour, String[] name);
	
	int readGame(Game game) throws SQLException;	
	void createPlayers(Game game) throws SQLException;
	ArrayList<Game> readAllGames() throws SQLException;
	ArrayList<Player> readPlayers(Game game) throws SQLException;
	ArrayList<Fields> readProperty(Game game) throws SQLException;
	void createGame(Game game) throws SQLException;
	void createProperties(Game game) throws SQLException;
	void updateProperties(Game game) throws SQLException;
	void updatePlayer(Game game) throws SQLException;
	void updateSaveDate(Game game) throws SQLException;
	void endGame(Game game) throws SQLException;
	
}
