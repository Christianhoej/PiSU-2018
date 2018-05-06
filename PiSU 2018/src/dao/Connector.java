package dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.CallableStatement;



public class Connector {
	private final String HOST     = "localhost";
	private final int    PORT     = 3306;
	private final String DATABASE = "matador_projekt";
	private final String USERNAME = "root"; 
	private final String PASSWORD = "";
	private Connection connection;


	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return connection;
	}
}
