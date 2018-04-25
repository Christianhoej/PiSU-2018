package dao;

import java.sql.Timestamp; 
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import gui_codebehind.Game;

public class TestGunnRun {
	private final static String HOST     = "localhost";
	private final static int    PORT     = 3306;
	private final static String DATABASE = "mydb1";
	private final static String USERNAME = "root"; 
	private final static String PASSWORD = "";
	private static Connection connection;
	static int[] playerID = new int[3];
	static int[] carID = new int[3];
	static int[] playerInfoID = new int[3];
	static int gameID;


	public static void main(String[] args) throws SQLException, ClassNotFoundException { 
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
		connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		System.out.println("Connected");

		//		

		gameID = createGame();
		String[] colour = {"Green", "Black", "Blue"};
		String[] name = {"Gunn", "Yoss", "Janus"};
		
		// CREATE
		for(int i = 0; i <carID.length; i++) {
			playerID[i] = createPlayer(gameID, name[i], colour[i]);
		}
		
		createProperties(gameID);

		//		createProperties(gameID);
		//		createAll();
		//		updateAll();
		//		ArrayList<Game> allGames = getAllGames();
		//		int newGameID = readGame(allGames.get(2).getStartDate());
		//		ArrayList<PlayerDAO> players = readPlayer(newGameID);
		//		System.out.println(players);
		//
		//		for(int i = 0; i<players.size(); i++) {
		//			ArrayList<PropertyDAO> prop = readProperty(newGameID, players.get(i).getPlayerID());
		//			System.out.println(prop);
		//		}
	}

	public static ArrayList<PropertyDAO> readProperty(int gameID, int playerID) throws SQLException {
		//		stmtultSet stmt = connection.doQuery("{call read_property("+gameID+","+playerID+")}");
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call read_property(?, ?)}");
		stmt.setInt(1, gameID);
		stmt.setInt(2, playerID);
		stmt.execute();
		ResultSet res = stmt.getResultSet();

		ArrayList<PropertyDAO> array = new ArrayList<PropertyDAO>();
		while(res.next()) {
			PropertyDAO property  = new PropertyDAO(stmt.getInt("fieldNumber"), stmt.getInt("houses")); 
			array.add(property);
		}
		return array;
	}



	public static ArrayList<PlayerDAO> readPlayer(int gameID) throws SQLException {
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call read_player(?)}");
		stmt.setInt(1, gameID);
		stmt.execute();
		ResultSet res = stmt.getResultSet();

		ArrayList<PlayerDAO> array = new ArrayList<PlayerDAO>();
		while(res.next()) {
			PlayerDAO player = new PlayerDAO(stmt.getInt("playerID"), stmt.getInt("playerInfoID"), stmt.getInt("carID"), stmt.getInt("prison"), stmt.getInt("getOutPrison"), 
					stmt.getInt("balance"), stmt.getBoolean("broke"), stmt.getBoolean("current"), stmt.getString("name")); 
			array.add(player);
		}
		return array;
	}

	public static ArrayList<Game> getAllGames() throws SQLException {

		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call get_allGames()}");
		stmt.execute();
		ResultSet res = stmt.getResultSet();

		ArrayList<Game> array = new ArrayList<Game>();
		while(res.next()) {
			Game game = new Game(stmt.getInt("gameID"), stmt.getString("gameName"), stmt.getInt("players"), stmt.getTimestamp("startDate"));
			array.add(game);
		}
		return array;
	}
	

	public static int readGame(Timestamp gameDate) throws SQLException {
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call read_game(?,?)}");
		stmt.setTimestamp(1, gameDate);
		stmt.registerOutParameter(2, Types.INTEGER);
		stmt.execute();
		int gameID = stmt.getInt(2);
		return gameID;
	}




	public static void createAll() throws SQLException { 
		String[] colour = {"Green", "Black", "Blue"};
		String[] name = {"Gunn", "Yoss", "Janus"};

		gameID = createGame();
		createProperties(gameID);
		// CREATE
		for(int i = 0; i <carID.length; i++) {
			playerID[i] = createPlayer(gameID, name[i], colour[i]);
		}
	}

	public static int createGame() throws SQLException {
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call create_game(?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.execute();
		int gameID = stmt.getInt(1);
		return gameID;

	}

	public static int createPlayer(int gameID, String name, String carColour) throws SQLException {
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call create_player(?,?,?,?)}");
		stmt.setInt(1, gameID);
		stmt.setString(2, name);
		stmt.setString(3, carColour);
		stmt.registerOutParameter(4, Types.INTEGER);
		stmt.execute();
		int playerID = stmt.getInt(4);
		return playerID;
	}

	public static void createProperties(int gameID) throws SQLException {
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call create_property(?)}");
		stmt.setInt(1, gameID);
		stmt.execute();
	}




	public static void updateAll() throws SQLException {
		// UPDATE

		int[] position = {1, 3, 5};
		int[] prison = {0, 0, 0};
		int[] getOutPrison = {0, 0, 0};
		int[] balance = {0, 0, 0};
		boolean[] broke = {false, false, false};
		boolean[] current = {true, false, false};

		int[] fieldNumber = {1, 3, 5};
		int[] houses = {-1, 0, 3};



		for(int i = 0; i <carID.length; i++) {
			updatePlayer(playerID[i], gameID, position[i], prison[i], getOutPrison[i], balance[i], broke[i], current[i]);
			updateProperties(gameID, fieldNumber[i], playerID[i], houses[i]);
		}
		updateSaveDate(gameID);
	}

	public static void updatePlayer(int playerID, int gameID, int position, int prison, int getOutPrison, int balance, boolean broke, boolean current) throws SQLException {
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call create_property(?,?,?,?,?,?,?,?)}");
		stmt.setInt(1, playerID);
		stmt.setInt(2, gameID);
		stmt.setInt(3, position);
		stmt.setInt(4, prison);
		stmt.setInt(5, getOutPrison);
		stmt.setInt(6, balance);
		stmt.setBoolean(7, broke);
		stmt.setBoolean(8, current);
		stmt.execute();
	}

	public static void updateProperties(int gameID, int fieldNumber, int playerID, int houses) throws SQLException {
		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call create_property(?,?,?,?)}");
		stmt.setInt(1, gameID);
		stmt.setInt(2, fieldNumber);
		stmt.setInt(3, playerID);
		stmt.setInt(4, houses);
		stmt.execute();		
	}

	public static void updateSaveDate(int gameID) throws SQLException {

		CallableStatement stmt = null;
		stmt = (CallableStatement) connection.prepareCall("{call create_property(?)}");
		stmt.setInt(1, gameID);
		stmt.execute();

	}
}
