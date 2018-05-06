package dao;

import java.sql.Connection; 
import java.sql.SQLException;   
import java.util.ArrayList;

import model.Fields;
import model.Game;
import model.Player;

public interface IGameDAO {

	ArrayList<Game> readAllGames() throws SQLException;
	
	ArrayList<Player> readPlayers(Game game) throws SQLException;
	
	ArrayList<Fields> readProperty(Game game) throws SQLException;
	
	void createGame(Game game) throws SQLException;
	
	void createProperties(Game game, Connection con) throws SQLException;
	
	void updateProperties(Game game) throws SQLException;
	
	void updatePlayer(Game game) throws SQLException;
	
	void updateSaveDate(Game game) throws SQLException;
	
	void endGame(Game game) throws SQLException;
	
	void createPlayers(Game game, Connection con) throws SQLException;
}
