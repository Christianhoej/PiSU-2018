package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import test.Connector;
import model.Property;
import model.RealEstate;
import model.Utility;
import model.Player;
import model.Fields;
import model.Game;

public class GameOAD implements IGameOAD 
{	
	private Connector connect = new Connector();

	@Override
	public void createGame(Game game) throws SQLException { 
		Connection con = connect.getConnection();
		CallableStatement stmt =null;
		stmt = (CallableStatement) con.prepareCall("{call create_game(?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.execute();
		game.setGameID(stmt.getInt(1));
		createPlayers(game);
		createProperties(game);
	}

	@Override
	public void createPlayers(Game game) throws SQLException {	
		Connection con = connect.getConnection();
		CallableStatement stmt = (CallableStatement) con.prepareCall("{call create_player(?,?,?,?)}");
		for (Player player: game.getPlayers()) {
			stmt.setInt(1, game.getGameID());
			stmt.setString(2, player.getName());
			stmt.setString(3, player.getColour());
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.execute();
			player.setPlayerID(stmt.getInt(4));
		}		
	}

	@Override
	public void createProperties(Game game) throws SQLException {
		Connection con = connect.getConnection();		
		CallableStatement stmt = (CallableStatement) con.prepareCall("{call create_property(?)}");
		stmt.setInt(1, game.getGameID());
		stmt.execute();
	}

	@Override
	public void updatePlayer(Game game) throws SQLException {
		Connection con = connect.getConnection();	
		ArrayList<Player> player = game.getPlayers();
		for(int i = 0; i<player.size(); i++) {
			CallableStatement stmt = (CallableStatement) con.prepareCall("{call update_player(?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, player.get(i).getPlayerID());
			stmt.setInt(2, game.getGameID());
			stmt.setInt(3, player.get(i).getPosition());
			stmt.setInt(4, player.get(i).getInPrison());
			stmt.setInt(5, player.get(i).getAccount().getPrisonCard());
			stmt.setInt(6, player.get(i).getAccount().getCash());
			stmt.setBoolean(7, player.get(i).isBroke());
			stmt.setBoolean(8, player.get(i).getCurrent());
			stmt.execute();
		}
	}

	// Nok overflødig
	@Override
	public int readGame(Game game) throws SQLException {
		//		Connection con = connect.getConnection();
		//		CallableStatement stmt = (CallableStatement) con.prepareCall("{call read_game(?,?)}");
		//		stmt.setTimestamp(1, gameDate);
		//		stmt.registerOutParameter(2, Types.INTEGER);
		//		stmt.execute();
		//		int gameID = stmt.getInt(2);
		//		return gameID;
		return 0;
	}

	@Override
	public ArrayList<Game> readAllGames() throws SQLException {
		Connection con = connect.getConnection();
		CallableStatement stmt = (CallableStatement) con.prepareCall("{call get_allGames()}");
		stmt.execute();
		ResultSet res = stmt.getResultSet();

		ArrayList<Game> array = new ArrayList<Game>();
		while(res.next()) {
			Game game = new Game();
			game.setGameID(res.getInt("gameID"));
			game.setPlayerAmount(res.getInt("players"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String date  = dateFormat.format(res.getTimestamp("startDate"));
			game.setGameDate(date);
			array.add(game);
		}
		return array;
	}


	@Override
	public ArrayList<Player> readPlayers(Game game) throws SQLException {
		Connection con = connect.getConnection();
		CallableStatement stmt = (CallableStatement) con.prepareCall("{call read_player(?)}");
		stmt.setInt(1, game.getGameID());
		stmt.execute();
		ResultSet res = stmt.getResultSet();
		ArrayList<Player> array = new ArrayList<Player>();
		while(res.next()) {
			Player player = new Player(res.getString("name"));
			player.setPlayerID(res.getInt("playerID"));
			player.setColour(res.getString("carColour"));
			player.setPosition(res.getInt("position"));
			player.setInPrison(res.getInt("prison"));
			player.getAccount().updatePrisonCard(res.getInt("getOutPrison")); 
			player.getAccount().updateCash(res.getInt("balance"));
			player.setBroke(res.getBoolean("broke"));
			player.setCurrent(res.getBoolean("current")); 
			array.add(player);
		}
		return array;
	}


	@Override
	public ArrayList<Fields> readProperty(Game game) throws SQLException {
		Connection con = connect.getConnection();
		ArrayList<Player> player = game.getPlayers();
		ArrayList<Fields> field = game.getFields();
		for(int i=0; i<player.size(); i++) {

			CallableStatement stmt = (CallableStatement) con.prepareCall("{call read_property(?, ?)}");
			stmt.setInt(1, game.getGameID());
			stmt.setInt(2, player.get(i).getPlayerID());
			stmt.execute();
			ResultSet res = stmt.getResultSet();

			while(res.next()) {

				field.get(res.getInt("fieldNumber")).setHouses(res.getInt("houses")); 
				field.get(res.getInt("fieldNumber")).setOwner(player.get(i));
				if(res.getInt("houses")==-1) {
					field.get(res.getInt("fieldNumber")).setMortage(true);
				}
				else field.get(res.getInt("fieldNumber")).setMortage(false);
				player.get(i).addOwnedProperties(res.getInt("fieldNumber"));
			}
		}
		return field;
	}

	@Override
	public void updateProperties(Game game) throws SQLException {
		Connection con = connect.getConnection();
		ArrayList<Fields> field = game.getFields();
		ArrayList<Player> player = game.getPlayers();

		for(int i = 0; i<player.size(); i++) {
			for(int j = 0; j<player.get(i).getOwnedProperties().length; j++) {
				CallableStatement stmt = (CallableStatement) con.prepareCall("{call update_property(?,?,?,?)}");
				if(player.get(i).getOwnedProperties()[j] == 1) {

					System.out.println("Der er en EJER!!!!!");
					stmt.setInt(1, game.getGameID());
					stmt.setInt(2, field.get(j).getFieldNumber());
					stmt.setInt(3, player.get(i).getPlayerID());
					stmt.setInt(4, field.get(j).getHouses());
					stmt.execute();
				}
			}
		}
	}

	@Override
	public void updateSaveDate(Game game) throws SQLException {
		Connection con = connect.getConnection();
		CallableStatement stmt = (CallableStatement) con.prepareCall("{call update_saveDate(?)}");
		stmt.setInt(1, game.getGameID());
		stmt.execute();

	}
	
	// Husk at tjekke for endGame, når vi har et spil at tjekke for.
	@Override
	public void endGame(Game game) throws SQLException {
		Connection con = connect.getConnection();
		CallableStatement stmt = (CallableStatement) con.prepareCall("{call end_game(?)}");
		stmt.setInt(1, game.getGameID());
		stmt.execute();

	}

}

