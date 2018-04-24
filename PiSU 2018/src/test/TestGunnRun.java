package test;

import java.sql.Timestamp; 
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.CallableStatement;

import gui_codebehind.Game;
import dao.PlayerDAO;
import dao.PropertyDAO;

public class TestGunnRun {
	private final static String HOST     = "localhost";
	private final static int    PORT     = 3306;
	private final static String DATABASE = "mydb1";
	private final static String USERNAME = "root"; 
	private final static String PASSWORD = "";
	private static Connect connection = new Connect();
	static int[] playerID = new int[3];
	static int[] carID = new int[3];
	static int[] playerInfoID = new int[3];
	static int gameID;


	public static void main(String[] args) throws SQLException { 
		//			Class.forName("com.mysql.jdbc.Driver");
		//			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
		//			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		//			System.out.println("Connected");

		createAll();
		updateAll();
		ArrayList<Game> allGames = getAllGames();
		int newGameID = readGame(allGames.get(2).getStartDate());
		ArrayList<PlayerDAO> players = readPlayer(newGameID);
		System.out.println(players);

		for(int i = 0; i<players.size(); i++) {
			ArrayList<PropertyDAO> prop = readProperty(newGameID, players.get(i).getPlayerID());
			System.out.println(prop);
		}
	}

	public static ArrayList<PropertyDAO> readProperty(int gameID, int playerID) throws SQLException {
		ResultSet res = connection.doQuery("{call read_property("+gameID+","+playerID+")}");
		//		CallableStatement getProperty = null;
		//		getProperty = (CallableStatement) connection.prepareCall("{call read_property(?, ?)}");
		//		getProperty.setInt(1, gameID);
		//		getProperty.setInt(2, playerID);
		//		ResultSet result = getProperty.executeQuery();

		ArrayList<PropertyDAO> array = new ArrayList<PropertyDAO>();
		while(res.next()) {
			PropertyDAO property  = new PropertyDAO(res.getInt("fieldNumber"), res.getInt("houses")); 
			array.add(property);
		}
		return array;
	}



	public static ArrayList<PlayerDAO> readPlayer(int gameID) throws SQLException {
		ResultSet res = connection.doQuery("{call read_player("+gameID+")}");

		//		CallableStatement getplayer = null;
		//		getplayer = (CallableStatement) connection.prepareCall("{call read_player(?)}");
		//		getplayer.setInt(1, gameID);
		//		ResultSet result = getplayer.executeQuery();

		ArrayList<PlayerDAO> array = new ArrayList<PlayerDAO>();
		while(res.next()) {
			PlayerDAO player = new PlayerDAO(res.getInt("playerID"), res.getInt("playerInfoID"), res.getInt("carID"), res.getInt("prison"), res.getInt("getOutPrison"), 
					res.getInt("balance"), res.getBoolean("broke"), res.getBoolean("current"), res.getString("name")); 
			array.add(player);
		}
		return array;
	}


	public static int readGame(Timestamp gameDate) throws SQLException {
		ResultSet res = connection.runReadProcedure("read_game", gameDate, Types.INTEGER);
		//		CallableStatement readGame = null;
		//		readGame = (CallableStatement) connection.prepareCall("{call read_game(?,?)}");
		//		readGame.setTimestamp(1, gameDate);
		//		readGame.registerOutParameter(2, Types.INTEGER);
		//		readGame.execute();
		int gameID = res.getInt(2);
		return gameID;
	}

	public static ArrayList<Game> getAllGames() throws SQLException {
		ResultSet res = connection.runReadProcedure("get_allGames");

		//		CallableStatement getAllGames = null;
		//		getAllGames = (CallableStatement) connection.prepareCall("{call get_allGames()}");

		ArrayList<Game> array = new ArrayList<Game>();
		while(res.next()) {
			Game game = new Game(res.getInt("gameID"), res.getString("gameName"), res.getInt("players"), res.getTimestamp("startDate"));
			array.add(game);
		}
		return array;
	}


	public static void createAll() throws SQLException { 
		String[] colour = {"Green", "Black", "Blue"};
		String[] name = {"Gunn", "Yoss", "Janus"};

		gameID = createGame();
		createProperties(gameID);
		//		cPlayerInfo(gameID);
		// CREATE
		for(int i = 0; i <carID.length; i++) {
			carID[i] = createCar(colour[i]);
			playerInfoID[i] = createPlayerInfo(name[i]);
			playerID[i] = createPlayer(gameID, carID[i], playerInfoID[i]);
		}
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
			updateCar(carID[i], position[i]);
			updatePlayer(playerID[i], prison[i], getOutPrison[i], balance[i], broke[i], current[i]);
			updateProperties(gameID, fieldNumber[i], playerID[i], houses[i]);
		}
		updateSaveDate(gameID);
	}


	public static int createGame() throws SQLException {
		ResultSet res = connection.runReadProcedure("create_game");

		//		CallableStatement addGame = null;
		//		addGame = (CallableStatement) connection.prepareCall("{call create_game(?)}");
		//		addGame.registerOutParameter(1, Types.INTEGER);
		//		addGame.execute();
		if(res.next()) {
			int gameID = res.getInt("gameID");
			return gameID;
		}
		else return 0;
	}

	public static int createCar(String colour) throws SQLException {
		ResultSet res = connection.runReadProcedure("create_car", colour);

		//		CallableStatement addCar = null;
		//		addCar = (CallableStatement) connection.prepareCall();
		//		addCar.setString(1, colour);
		//		addCar.registerOutParameter(2, Types.INTEGER);
		//
		//		addCar.execute();
		if(res.next()) {
			int carID = res.getInt("carID");
			return carID;
		} else return 0;
	}

	public static int createPlayerInfo(String name) throws SQLException {
		ResultSet res = connection.runReadProcedure("create_playerInfo", name);
		//		CallableStatement addPlayerInfo = null;
		//		addPlayerInfo = (CallableStatement) connection.prepareCall("{call create_playerInfo(?,?)}");
		//		addPlayerInfo.setString(1, name);
		//		addPlayerInfo.registerOutParameter(2, Types.INTEGER);
		//		addPlayerInfo.execute();
		if(res.next()) {
			int playerInfoID = res.getInt("playerInfoID");
			return playerInfoID;
		} else return 0;
	}

	public static int createPlayer(int carID, int gameID, int playerInfoID) throws SQLException {
		ResultSet res = connection.runReadProcedure("create_player", carID, gameID, playerInfoID);

		//		CallableStatement addPlayer = null;
		//		addPlayer = (CallableStatement) connection.prepareCall("{call create_player(?,?,?,?)}");
		//		addPlayer.setInt(1, carID);
		//		addPlayer.setInt(2, gameID);
		//		addPlayer.setInt(3, playerInfoID);
		//		addPlayer.registerOutParameter(4, Types.INTEGER);
		//		addPlayer.execute();
		if(res.next()) {
			int playerID = res.getInt("playerID");
			return playerID;
		}
		else return 0;
	}

	public static void createProperties(int gameID) throws SQLException {
		connection.runReadProcedure("create_property", gameID);
		//		CallableStatement addProperty = null;
		//		addProperty = (CallableStatement) connection.prepareCall("{call create_property(?)}");
		//		addProperty.setInt(1, gameID);
		//		addProperty.execute();
	}


	public static void updateCar(int carID, int position) throws SQLException {
		connection.runUpdateProcedure("update_car", carID, position);
	}

	public static void updatePlayer(int playerID, int prison, int getOutPrison, int balance, boolean broke, boolean current) throws SQLException {
		connection.runUpdateProcedure("update_player", playerID, prison, getOutPrison, balance, broke, current);
	}

	public static void updateProperties(int gameID, int fieldNumber, int playerID, int houses) throws SQLException {
		connection.runUpdateProcedure("update_property",gameID, fieldNumber, playerID, houses);
	}

	public static void updateSaveDate(int gameID) throws SQLException {
		connection.runUpdateProcedure("update_saveDate", gameID);
	}

	public static void update() throws SQLException {
		connection.runUpdateProcedure("update_property", 14, 6, 42, 2);
	}
}
