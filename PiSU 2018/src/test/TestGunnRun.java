package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;


import com.mysql.jdbc.CallableStatement;

public class TestGunnRun {
	private final static String HOST     = "localhost";
    private final static int    PORT     = 3306;
    private final static String DATABASE = "mydb";
    private final static String USERNAME = "root"; 
    private final static String PASSWORD = "";
    private static Connection connection=null;
    
    
	public static void main(String[] args) throws SQLException {
		//		TestGunnDatabase db = new TestGunnDatabase();
		//		
		//		db.runProcedure("add_car", "black", 0);
		//		
		//	}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			System.out.println("Connected");
			
			
			int gameID = createGame();
			int carID = createCar("Green");
			int playerInfoID = createPlayerInfo("Gunn");
			
			int playerID = createPlayer(gameID, carID, playerInfoID);
			createProperties();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static int createGame() throws SQLException {
		CallableStatement addGame = null;
		addGame = (CallableStatement) connection.prepareCall("{call add_game(?)}");
		addGame.registerOutParameter(1, Types.INTEGER);
		addGame.execute();
		int gameID = addGame.getInt(1);
		
		System.out.println(gameID);
		return gameID;
	}
	
	public static int createCar(String colour) throws SQLException {
		CallableStatement addCar = null;
		addCar = (CallableStatement) connection.prepareCall("{call add_car(?,?)}");
		addCar.setString(1, colour);
		addCar.registerOutParameter(2, Types.INTEGER);
		
		addCar.execute();
		
		int carID = addCar.getInt(2);
		
		System.out.println(carID);
		return carID;
	}
	
	public static int createPlayerInfo(String name) throws SQLException {
		CallableStatement addPlayerInfo = null;
		addPlayerInfo = (CallableStatement) connection.prepareCall("{call add_playerInfo(?,?)}");
		addPlayerInfo.setString(1, name);
		addPlayerInfo.registerOutParameter(2, Types.INTEGER);
		addPlayerInfo.execute();
		int playerInfoID = addPlayerInfo.getInt(2);
		
		System.out.println(playerInfoID);
		
		return playerInfoID;
	}
	
	public static int createPlayer(int carID, int gameID, int playerInfoID) throws SQLException {
		CallableStatement addPlayer = null;
		addPlayer = (CallableStatement) connection.prepareCall("{call add_player(?,?,?,?)}");
		addPlayer.setInt(1, carID);
		addPlayer.setInt(2, gameID);
		addPlayer.setInt(3, playerInfoID);
		addPlayer.registerOutParameter(4, Types.INTEGER);
		addPlayer.execute();
		int playerID = addPlayer.getInt(4);
		
		System.out.println(playerID);
		return playerID;
	}
	
	public static void createProperties() throws SQLException {
		CallableStatement addProperty = null;
		addProperty = (CallableStatement) connection.prepareCall("{call add_property()}");
		addProperty.execute();
	}
	
}
