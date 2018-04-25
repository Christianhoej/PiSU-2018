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
import model.Player;
import model.Game;

public class GameOAD implements IGameOAD 
{	
	private Player player;
	private final String HOST     = "localhost";
	private final int    PORT     = 3306;
	private final String DATABASE = "mydb1";
	private final String USERNAME = "root"; 
	private final String PASSWORD = "";
	//private Connector connector;
	private Connector connect = new Connector();

	//	private static final String PLAYER_GAMEID = "gameID";

	//	public void createAll(String[] colour, String[] name) throws SQLException { 
	//		int gameID = createGame();
	//		
	//		// CREATE
	////		for(int i = 0; i <colour.length; i++) {
	////			playerID[i] = createPlayer(gameID, name[i], colour[i]);
	////		}
	//		
	//		createProperties(gameID);
	//	}


	//	public Connection Connector() {
	//        try {
	//			Class.forName("com.mysql.jdbc.Driver");
	//			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
	//			return con = DriverManager.getConnection(url, USERNAME, PASSWORD);
	//		} catch (ClassNotFoundException | SQLException e) {
	//			e.printStackTrace();
	//			System.exit(1);
	//		}
	//		return con;
	//    }


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
	public void updateAll() {
		// TODO Auto-generated method stub

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
	public ArrayList<Property> readProperty(Game game) throws SQLException {
		Connection con = connect.getConnection();
		ArrayList<Player> player = game.getPlayers();
		ArrayList<Property> property = game.getProperties();
		for(int i=0; i<player.size(); i++) {

			CallableStatement stmt = (CallableStatement) con.prepareCall("{call read_property(?, ?)}");
			stmt.setInt(1, game.getGameID());
			stmt.setInt(2, player.get(i).getPlayerID());
			stmt.execute();
			ResultSet res = stmt.getResultSet();

			while(res.next()) {
				property.get(res.getInt("fieldNumber")).setHouses(stmt.getInt("houses")); 
				property.get(res.getInt("fieldNumber")).setOwner(player.get(i));
				if(res.getInt("houses")==-1) {
					property.get(res.getInt("fieldNumber")).setMortage(true);
				}
				else property.get(res.getInt("fieldNumber")).setMortage(false);
				player.get(i).addOwnedProperties(res.getInt("fieldNumber"));
			}
		}
		return property;
	}

	public void updateProperties(Game game) throws SQLException {
		Connection con = connect.getConnection();
		ArrayList<Property> property = game.getProperties();
		ArrayList<Player> player = game.getPlayers();

		// Two for loops, so we get every compination of players
		for(int i = 0; i<player.size(); i++) {
			for(int j = 0; j<property.size(); j++) {
				CallableStatement stmt = (CallableStatement) con.prepareCall("{call update_property(?,?,?,?)}");
				if(property.get(j).getOwner() == player.get(i)) {

					stmt.setInt(1, game.getGameID());
					stmt.setInt(2, property.get(j).getFieldNumber());
					stmt.setInt(3, player.get(i).getPlayerID());
					stmt.setInt(4, property.get(j).getHouses());
					stmt.execute();
				}
			}
		}
	}


		//	public void updateAll() throws SQLException {
		//		// UPDATE
		//
		//		int[] position = {1, 3, 5};
		//		int[] prison = {0, 0, 0};
		//		int[] getOutPrison = {0, 0, 0};
		//		int[] balance = {0, 0, 0};
		//		boolean[] broke = {false, false, false};
		//		boolean[] current = {true, false, false};
		//
		//		int[] fieldNumber = {1, 3, 5};
		//		int[] houses = {-1, 0, 3};
		//
		//
		//
		//		for(int i = 0; i <carID.length; i++) {
		//			updatePlayer(playerID[i], gameID, position[i], prison[i], getOutPrison[i], balance[i], broke[i], current[i]);
		//			updateProperties(gameID, fieldNumber[i], playerID[i], houses[i]);
		//		}
		//		updateSaveDate(gameID);
		//	}
		//
		//	public void updatePlayer(int playerID, int gameID, int position, int prison, int getOutPrison, int balance, boolean broke, boolean current) throws SQLException {
		//		CallableStatement stmt = null;
		//		stmt = (CallableStatement) connector.prepareCall("{call create_property(?,?,?,?,?,?,?,?)}");
		//		stmt.setInt(1, playerID);
		//		stmt.setInt(2, gameID);
		//		stmt.setInt(3, position);
		//		stmt.setInt(4, prison);
		//		stmt.setInt(5, getOutPrison);
		//		stmt.setInt(6, balance);
		//		stmt.setBoolean(7, broke);
		//		stmt.setBoolean(8, current);
		//		stmt.execute();
		//	}
		//
		//	public void updateProperties(int gameID, int fieldNumber, int playerID, int houses) throws SQLException {
		//		CallableStatement stmt = null;
		//		stmt = (CallableStatement) connector.prepareCall("{call create_property(?,?,?,?)}");
		//		stmt.setInt(1, gameID);
		//		stmt.setInt(2, fieldNumber);
		//		stmt.setInt(3, playerID);
		//		stmt.setInt(4, houses);
		//		stmt.execute();		
		//	}
		//
		//	public void updateSaveDate(int gameID) throws SQLException {
		//
		//		CallableStatement stmt = null;
		//		stmt = (CallableStatement) connector.prepareCall("{call create_property(?)}");
		//		stmt.setInt(1, gameID);
		//		stmt.execute();
		//
		//	}
		//
		//
		//	@Override
		//	public void createAll() {
		//		// TODO Auto-generated method stub
		//		
		//	}

	}

